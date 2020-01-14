/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.ui.preview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component("contextProvider")
public class ContextProvider implements ApplicationContextAware
{
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        ContextProvider.context = context;
    }

    public static <T> T load(URL location) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(location);
        loader.setControllerFactory(context::getBean);
        return loader.load();
    }
}
