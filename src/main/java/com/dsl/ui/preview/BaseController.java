/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.ui.preview;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import org.tbee.javafx.scene.layout.fxml.MigPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

@Component("baseController")
public class BaseController implements Initializable
{
    @FXML
    private TreeView<Input> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        treeView.setRoot(new TreeItem<>(new Input("Root", "Root")));
        treeView.getRoot().getChildren().add(new TreeItem<>(new Input("TextField", "TextField")));
        treeView.getRoot().getChildren().add(new TreeItem<>(new Input("Radio Button", "RadioButton")));
        treeView.getRoot().getChildren().add(new TreeItem<>(new Input("Dropdown", "Dropdown")));
        treeView.setCellFactory(cell -> new CustomTreeCell());
        treeView.getRoot().setExpanded(true);
        treeView.setFixedCellSize(50);
    }

    private void preview(Input input)
    {
        System.out.println("Label: " + input.getLabel() + " Type: " + input.getType());
        InputComponent component = InputFactory.create(input);
        if(Objects.nonNull(component))
        {
            Dialog<Node> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(component.getNode());
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.showAndWait();
        }
    }

    public class CustomTreeCell extends TreeCell<Input>
    {
        private MigPane controls;
        private Label label = new Label();

        public CustomTreeCell()
        {
            controls = new MigPane();
            controls.setLayout("ins 10");
            controls.setCols("[]push[][]");

            Button edit = new Button("Edit");
            Button preview = new Button("Preview");

            preview.setOnAction(event -> preview(getItem()));

            controls.getChildren().addAll(label, edit, preview);
        }


        @Override
        protected void updateItem(Input item, boolean empty)
        {
            super.updateItem(item, empty);


            if(Objects.isNull(item) || empty)
            {
                setGraphic(null);
            }
            else
            {
                label.setText(item.getLabel());
                setGraphic(controls);
            }
        }
    }
}
