/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.ui.preview;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.tbee.javafx.scene.layout.fxml.MigPane;

import java.util.Objects;

public class TextInput implements InputComponent
{
    private Input input;
    private MigPane node;

    public TextInput(Input input)
    {
        this.input = input;
    }

    @Override
    public Node getNode()
    {
        if(Objects.isNull(node))
        {
            node = new MigPane();
            node.setLayout("ins 10, wrap");
            node.setCols("[]10[grow]");
            node.setRows("[sg]");

            node.getChildren().addAll(new Label(input.getLabel()), new TextField());
        }
        return node;
    }
}
