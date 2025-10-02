package com.example.client.view;

import com.example.client.models.Application;
import com.example.client.services.ApiService;
import com.example.client.util.Mediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ApplicationsManagement extends VBox {

    private ApiService apiService = ApiService.getInstance();
    private TableView<Application> applicationsTable;
    private ObservableList<Application> applicationsList;
    private Mediator mediator;

    public ApplicationsManagement(Mediator mediator) {
        this.mediator = mediator;

        setPadding(new Insets(30));
        setSpacing(20);
        setStyle("-fx-background-color: #f0f9ff;");

        VBox header = createHeader();
        HBox buttonBar = createButtonBar();
        applicationsTable = createApplicationsTable();

        VBox.setVgrow(applicationsTable, Priority.ALWAYS);

        getChildren().addAll(header, buttonBar, applicationsTable);

        loadApplications();
    }

    private VBox createHeader() {
        VBox header = new VBox(5);

        Label titleLabel = new Label("Applications Management");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#1e293b"));

        Label subtitleLabel = new Label("View and manage job applications");
        subtitleLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));
        subtitleLabel.setTextFill(Color.web("#64748b"));

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    private HBox createButtonBar() {
        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER_LEFT);

        Button deleteButton = createStyledButton("Delete Application", "#ef4444", "#dc2626");
        deleteButton.setOnAction(e -> deleteSelectedApplication());

        Button refreshButton = createStyledButton("Refresh", "#3b82f6", "#2563eb");
        refreshButton.setOnAction(e -> loadApplications());

        Button viewResumeButton = createStyledButton("View Resume", "#10b981", "#059669");
        viewResumeButton.setOnAction(e -> viewSelectedResume());

        buttonBar.getChildren().addAll(deleteButton, refreshButton, viewResumeButton);

        return buttonBar;
    }

    private Button createStyledButton(String text, String normalColor, String hoverColor) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setPrefWidth(150);

        String normalStyle =
                "-fx-background-color: " + normalColor + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 6, 0, 0, 3);";

        String hoverStyle =
                "-fx-background-color: " + hoverColor + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 5);";

        button.setStyle(normalStyle);
        button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
        button.setOnMouseExited(e -> button.setStyle(normalStyle));

        return button;
    }

    private TableView<Application> createApplicationsTable() {
        TableView<Application> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 12; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 4);"
        );

        TableColumn<Application, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(60);

        TableColumn<Application, String> userNameColumn = new TableColumn<>("Applicant Name");
        userNameColumn.setCellValueFactory(cellData -> {
            Application app = cellData.getValue();
            String name = app.getUser() != null ? app.getUser().getName() : "Unknown";
            return new javafx.beans.property.SimpleStringProperty(name);
        });
        userNameColumn.setPrefWidth(180);

        TableColumn<Application, String> userEmailColumn = new TableColumn<>("Email");
        userEmailColumn.setCellValueFactory(cellData -> {
            Application app = cellData.getValue();
            String email = app.getUser() != null ? app.getUser().getEmail() : "Unknown";
            return new javafx.beans.property.SimpleStringProperty(email);
        });
        userEmailColumn.setPrefWidth(200);

        TableColumn<Application, String> jobTitleColumn = new TableColumn<>("Job Title");
        jobTitleColumn.setCellValueFactory(cellData -> {
            Application app = cellData.getValue();
            String title = app.getJob() != null ? app.getJob().getTitle() : "Unknown";
            return new javafx.beans.property.SimpleStringProperty(title);
        });
        jobTitleColumn.setPrefWidth(200);

        TableColumn<Application, String> resumeColumn = new TableColumn<>("Resume");
        resumeColumn.setCellValueFactory(new PropertyValueFactory<>("resumeUrl"));
        resumeColumn.setPrefWidth(250);

        TableColumn<Application, Instant> appliedAtColumn = new TableColumn<>("Applied At");
        appliedAtColumn.setCellValueFactory(new PropertyValueFactory<>("appliedAt"));
        appliedAtColumn.setPrefWidth(180);
        appliedAtColumn.setCellFactory(column -> new TableCell<Application, Instant>() {
            private DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")
                    .withZone(ZoneId.systemDefault());

            @Override
            protected void updateItem(Instant item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(formatter.format(item));
                }
            }
        });

        table.getColumns().addAll(idColumn, userNameColumn, userEmailColumn,
                jobTitleColumn, resumeColumn, appliedAtColumn);

        applicationsList = FXCollections.observableArrayList();
        table.setItems(applicationsList);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    private void loadApplications() {
        System.out.println("=== LOADING APPLICATIONS ===");
        new Thread(() -> {
            try {
                var applications = apiService.getAllApplications();
                System.out.println("Received " + applications.size() + " applications from backend");

                for (Application app : applications) {
                    System.out.println("App ID: " + app.getId() +
                                     ", User: " + (app.getUser() != null ? app.getUser().getName() : "null") +
                                     ", Job: " + (app.getJob() != null ? app.getJob().getTitle() : "null"));
                }

                javafx.application.Platform.runLater(() -> {
                    applicationsList.clear();
                    applicationsList.addAll(applications);
                    System.out.println("Table now has " + applicationsList.size() + " items");
                });
            } catch (IOException e) {
                System.err.println("ERROR loading applications: " + e.getMessage());
                e.printStackTrace();
                javafx.application.Platform.runLater(() ->
                        showAlert("Error", "Failed to load applications: " + e.getMessage(), Alert.AlertType.ERROR)
                );
            }
        }).start();
    }

    private void deleteSelectedApplication() {
        Application selectedApp = applicationsTable.getSelectionModel().getSelectedItem();

        if (selectedApp == null) {
            showAlert("Warning", "Please select an application to delete", Alert.AlertType.WARNING);
            return;
        }

        String applicantName = selectedApp.getUser() != null ? selectedApp.getUser().getName() : "Unknown";

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Application");
        confirmAlert.setHeaderText("Delete application from: " + applicantName + "?");
        confirmAlert.setContentText("This action cannot be undone.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                new Thread(() -> {
                    try {
                        apiService.deleteApplication(selectedApp.getId());
                        javafx.application.Platform.runLater(() -> {
                            loadApplications();
                            showAlert("Success", "Application deleted successfully!", Alert.AlertType.INFORMATION);
                        });
                    } catch (IOException e) {
                        javafx.application.Platform.runLater(() ->
                                showAlert("Error", "Failed to delete application: " + e.getMessage(), Alert.AlertType.ERROR)
                        );
                    }
                }).start();
            }
        });
    }

    private void viewSelectedResume() {
        Application selectedApp = applicationsTable.getSelectionModel().getSelectedItem();

        if (selectedApp == null) {
            showAlert("Warning", "Please select an application to view resume", Alert.AlertType.WARNING);
            return;
        }

        String resumeUrl = selectedApp.getResumeUrl();
        if (resumeUrl == null || resumeUrl.isEmpty()) {
            showAlert("Info", "No resume URL available", Alert.AlertType.INFORMATION);
            return;
        }

        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(resumeUrl));
        } catch (Exception e) {
            showAlert("Error", "Failed to open resume: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}