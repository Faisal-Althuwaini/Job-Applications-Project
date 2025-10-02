package com.example.client.view;


import com.example.client.util.Mediator;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashBoard {

    private Stage stage;
    private Mediator mediator;

    public DashBoard(Stage stage) {
        this.stage = stage;
    }

    public Scene createScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0f9ff;");

        mediator = new Mediator(root);

        Sidebar sidebar = new Sidebar(mediator, stage);
        root.setLeft(sidebar);

        JobsManagement jobsView = new JobsManagement(mediator);
        ApplicationsManagement applicationsView = new ApplicationsManagement(mediator);

        mediator.registerView("Jobs", jobsView);
        mediator.registerView("Applications", applicationsView);

        mediator.switchView("Jobs");

        return new Scene(root, 1400, 800);
    }
}