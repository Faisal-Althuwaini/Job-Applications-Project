package com.example.client;

import com.example.client.view.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Login loginView = new Login(primaryStage);
        Scene loginScene = loginView.createScene();

        primaryStage.setTitle("Admin Login");
        primaryStage.setScene(loginScene);
        primaryStage.setMinWidth(450);
        primaryStage.setMinHeight(550);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
