/*
 * Author Steven Yeoh
 * Copyright (c) 2020. All rights reserved
 */

package com.dsl.ui.preview;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application
{
    private ConfigurableApplicationContext context;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        context = SpringApplication.run(Main.class);
        context.getAutowireCapableBeanFactory().autowireBean(this);

        Parent root = ContextProvider.load(getClass().getResource("/Base.fxml"));

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception
    {
        context.stop();
    }
}
