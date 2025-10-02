package com.example.client.view;

import com.example.client.services.ApiService;
import com.example.client.util.Mediator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Sidebar extends VBox {

    private Button selectedButton = null;
    private final List<Button> buttons = new ArrayList<>();
    private final Mediator mediator;
    private final Stage stage;
    private final ApiService apiService = ApiService.getInstance();

    public Sidebar(Mediator mediator, Stage stage) {
        this.mediator = mediator;
        this.stage = stage;

        setPadding(new Insets(30, 20, 30, 20));
        setPrefWidth(250);
        setMinWidth(250);
        setMaxWidth(250);
        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);
        setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: #e5e7eb; " +
                        "-fx-border-width: 0 1 0 0;"
        );

        Label titleLabel = new Label("HIRE");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setStyle("-fx-text-fill: linear-gradient(to right, #38bdf8, #3b82f6);");
        titleLabel.setPadding(new Insets(10));

        Button jobsButton = createSidebarButton("Jobs Management", "Jobs");
        Button applicationsButton = createSidebarButton("Applications", "Applications");

        buttons.add(jobsButton);
        buttons.add(applicationsButton);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Label greetingLabel = new Label("Welcome, Admin");
        greetingLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));
        greetingLabel.setTextFill(Color.web("#6b7280"));
        greetingLabel.setAlignment(Pos.CENTER);
        greetingLabel.setMaxWidth(Double.MAX_VALUE);

        Button logoutButton = new Button("Logout");
        logoutButton.setMaxWidth(Double.MAX_VALUE);
        logoutButton.setPrefHeight(40);
        logoutButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #dc2626, #b91c1c); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-effect: dropshadow(gaussian, rgba(220,38,38,0.3), 8, 0, 0, 4);"
        );
        logoutButton.setOnMouseEntered(e -> {
            logoutButton.setStyle(
                    "-fx-background-color: linear-gradient(to right, #b91c1c, #991b1b); " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14; " +
                            "-fx-font-weight: bold; " +
                            "-fx-cursor: hand; " +
                            "-fx-background-radius: 8; " +
                            "-fx-effect: dropshadow(gaussian, rgba(220,38,38,0.5), 12, 0, 0, 6);"
            );
        });
        logoutButton.setOnMouseExited(e -> {
            logoutButton.setStyle(
                    "-fx-background-color: linear-gradient(to right, #dc2626, #b91c1c); " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14; " +
                            "-fx-font-weight: bold; " +
                            "-fx-cursor: hand; " +
                            "-fx-background-radius: 8; " +
                            "-fx-effect: dropshadow(gaussian, rgba(220,38,38,0.3), 8, 0, 0, 4);"
            );
        });
        logoutButton.setOnAction(e -> handleLogout());

        getChildren().addAll(
                titleLabel,
                jobsButton,
                applicationsButton,
                spacer,
                greetingLabel,
                logoutButton
        );

        selectButton(jobsButton);
    }

    private Button createSidebarButton(String text, String view) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(45);
        button.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-text-fill: #374151; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: normal; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-alignment: CENTER-LEFT; " +
                        "-fx-padding: 12 20;"
        );

        button.setOnMouseEntered(e -> {
            if (selectedButton != button) {
                button.setStyle(
                        "-fx-background-color: #f0f9ff; " +
                                "-fx-text-fill: #0ea5e9; " +
                                "-fx-font-size: 14; " +
                                "-fx-font-weight: normal; " +
                                "-fx-cursor: hand; " +
                                "-fx-background-radius: 8; " +
                                "-fx-alignment: CENTER-LEFT; " +
                                "-fx-padding: 12 20;"
                );
            }
        });

        button.setOnMouseExited(e -> {
            if (selectedButton != button) {
                button.setStyle(
                        "-fx-background-color: transparent; " +
                                "-fx-text-fill: #374151; " +
                                "-fx-font-size: 14; " +
                                "-fx-font-weight: normal; " +
                                "-fx-cursor: hand; " +
                                "-fx-background-radius: 8; " +
                                "-fx-alignment: CENTER-LEFT; " +
                                "-fx-padding: 12 20;"
                );
            }
        });

        button.setOnAction(e -> {
            selectButton(button);
            mediator.switchView(view);
        });

        return button;
    }

    private void selectButton(Button button) {
        if (selectedButton != null) {
            selectedButton.setStyle(
                    "-fx-background-color: transparent; " +
                            "-fx-text-fill: #374151; " +
                            "-fx-font-size: 14; " +
                            "-fx-font-weight: normal; " +
                            "-fx-cursor: hand; " +
                            "-fx-background-radius: 8; " +
                            "-fx-alignment: CENTER-LEFT; " +
                            "-fx-padding: 12 20;"
            );
        }
        selectedButton = button;
        selectedButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #dbeafe, #bfdbfe); " +
                        "-fx-text-fill: #0284c7; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-alignment: CENTER-LEFT; " +
                        "-fx-padding: 12 20; " +
                        "-fx-border-color: #38bdf8; " +
                        "-fx-border-width: 0 0 0 3; " +
                        "-fx-border-insets: 0;"
        );
    }

    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("You will be returned to the login screen.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                apiService.logout();
                Login loginView = new Login(stage);
                Scene loginScene = loginView.createScene();
                stage.setScene(loginScene);
                stage.setTitle("Admin Login - HIRE");
                stage.setMaximized(false);
                stage.setWidth(550);
                stage.setHeight(700);
                stage.centerOnScreen();
            }
        });
    }
}
