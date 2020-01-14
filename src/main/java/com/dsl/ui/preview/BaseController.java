/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.ui.preview;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import org.tbee.javafx.scene.layout.fxml.MigPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component("baseController")
public class BaseController implements Initializable
{
    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        treeView.setRoot(new TreeItem<>("Root"));
        treeView.getRoot().getChildren().add(new TreeItem<>("TextField"));
        treeView.getRoot().getChildren().add(new TreeItem<>("TextField"));
        treeView.getRoot().getChildren().add(new TreeItem<>("Dropdown"));
        treeView.setCellFactory(cell -> new CustomTreeCell());
        treeView.setFixedCellSize(50);
        treeView.getRoot().setExpanded(true);
    }

    public class CustomTreeCell extends TreeCell<String>
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

            controls.getChildren().addAll(label, edit, preview);
        }

        @Override
        protected void updateItem(String item, boolean empty)
        {
            super.updateItem(item, empty);


            if(Objects.isNull(item) || empty)
            {
                setGraphic(null);
            }
            else
            {
                label.setText(item);
                setGraphic(controls);
            }
        }
    }
}
