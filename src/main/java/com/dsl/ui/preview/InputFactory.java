/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.ui.preview;

public class InputFactory
{
    public static InputComponent create(Input input)
    {
        switch (input.getType())
        {
            case "TextField":
                return new TextInput(input);
        }
        return null;
    }
}
