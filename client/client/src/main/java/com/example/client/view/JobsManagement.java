package com.example.client.view;

import com.example.client.models.Job;
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

public class JobsManagement extends VBox {

    private ApiService apiService = ApiService.getInstance();
    private TableView<Job> jobsTable;
    private ObservableList<Job> jobsList;
    private Mediator mediator;

    public JobsManagement(Mediator mediator) {
        this.mediator = mediator;

        setPadding(new Insets(30));
        setSpacing(20);
        setStyle("-fx-background-color: #f0f9ff;");

        VBox header = createHeader();
        HBox buttonBar = createButtonBar();
        jobsTable = createJobsTable();

        VBox.setVgrow(jobsTable, Priority.ALWAYS);

        getChildren().addAll(header, buttonBar, jobsTable);

        loadJobs();
    }

    private VBox createHeader() {
        VBox header = new VBox(5);

        Label titleLabel = new Label("Jobs Management");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#1e293b"));

        Label subtitleLabel = new Label("Manage job postings and listings");
        subtitleLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));
        subtitleLabel.setTextFill(Color.web("#64748b"));

        header.getChildren().addAll(titleLabel, subtitleLabel);
        return header;
    }

    private HBox createButtonBar() {
        HBox buttonBar = new HBox(10);
        buttonBar.setAlignment(Pos.CENTER_LEFT);

        Button addButton = createStyledButton("Add Job", "#10b981", "#059669");
        addButton.setOnAction(e -> showAddJobDialog());

        Button deleteButton = createStyledButton("Delete Job", "#ef4444", "#dc2626");
        deleteButton.setOnAction(e -> deleteSelectedJob());

        Button refreshButton = createStyledButton("Refresh", "#3b82f6", "#2563eb");
        refreshButton.setOnAction(e -> loadJobs());

        buttonBar.getChildren().addAll(addButton, deleteButton, refreshButton);

        return buttonBar;
    }

    private Button createStyledButton(String text, String normalColor, String hoverColor) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setPrefWidth(120);

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

    private TableView<Job> createJobsTable() {
        TableView<Job> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 12; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 4);"
        );

        TableColumn<Job, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setPrefWidth(60);

        TableColumn<Job, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(250);

        TableColumn<Job, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setPrefWidth(150);

        TableColumn<Job, String> departmentColumn = new TableColumn<>("Department");
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        departmentColumn.setPrefWidth(150);

        TableColumn<Job, Instant> postedAtColumn = new TableColumn<>("Posted At");
        postedAtColumn.setCellValueFactory(new PropertyValueFactory<>("postedAt"));
        postedAtColumn.setPrefWidth(180);
        postedAtColumn.setCellFactory(column -> new TableCell<Job, Instant>() {
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

        TableColumn<Job, String> postedByColumn = new TableColumn<>("Posted By");
        postedByColumn.setCellValueFactory(cellData -> {
            Job job = cellData.getValue();
            String postedBy = job.getPostedBy() != null ? job.getPostedBy().getName() : "Unknown";
            return new javafx.beans.property.SimpleStringProperty(postedBy);
        });
        postedByColumn.setPrefWidth(150);

        table.getColumns().addAll(idColumn, titleColumn, locationColumn,
                departmentColumn, postedAtColumn, postedByColumn);

        jobsList = FXCollections.observableArrayList();
        table.setItems(jobsList);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    private void loadJobs() {
        new Thread(() -> {
            try {
                var jobs = apiService.getAllJobs();
                javafx.application.Platform.runLater(() -> {
                    jobsList.clear();
                    jobsList.addAll(jobs);
                });
            } catch (IOException e) {
                javafx.application.Platform.runLater(() ->
                        showAlert("Error", "Failed to load jobs: " + e.getMessage(), Alert.AlertType.ERROR)
                );
            }
        }).start();
    }

    private void showAddJobDialog() {
        Dialog<Job> dialog = new Dialog<>();
        dialog.setTitle("Add New Job");
        dialog.setHeaderText("Enter job details");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));

        TextField titleField = new TextField();
        titleField.setPromptText("Job Title");
        titleField.setPrefWidth(300);
        styleTextField(titleField);

        TextArea descriptionArea = new TextArea();
        descriptionArea.setPromptText("Job Description");
        descriptionArea.setPrefRowCount(4);
        descriptionArea.setPrefWidth(300);
        styleTextArea(descriptionArea);

        TextField locationField = new TextField();
        locationField.setPromptText("Location");
        locationField.setPrefWidth(300);
        styleTextField(locationField);

        TextField departmentField = new TextField();
        departmentField.setPromptText("Department");
        departmentField.setPrefWidth(300);
        styleTextField(departmentField);

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("Location:"), 0, 2);
        grid.add(locationField, 1, 2);
        grid.add(new Label("Department:"), 0, 3);
        grid.add(departmentField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    return apiService.createJob(
                            titleField.getText(),
                            descriptionArea.getText(),
                            locationField.getText(),
                            departmentField.getText()
                    );
                } catch (IOException e) {
                    javafx.application.Platform.runLater(() ->
                            showAlert("Error", "Failed to create job: " + e.getMessage(), Alert.AlertType.ERROR)
                    );
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(job -> {
            if (job != null) {
                loadJobs();
                showAlert("Success", "Job created successfully!", Alert.AlertType.INFORMATION);
            }
        });
    }

    private void deleteSelectedJob() {
        Job selectedJob = jobsTable.getSelectionModel().getSelectedItem();

        if (selectedJob == null) {
            showAlert("Warning", "Please select a job to delete", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Job");
        confirmAlert.setHeaderText("Delete job: " + selectedJob.getTitle() + "?");
        confirmAlert.setContentText("This action cannot be undone.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                new Thread(() -> {
                    try {
                        apiService.deleteJob(selectedJob.getId());
                        javafx.application.Platform.runLater(() -> {
                            loadJobs();
                            showAlert("Success", "Job deleted successfully!", Alert.AlertType.INFORMATION);
                        });
                    } catch (IOException e) {
                        javafx.application.Platform.runLater(() ->
                                showAlert("Error", "Failed to delete job: " + e.getMessage(), Alert.AlertType.ERROR)
                        );
                    }
                }).start();
            }
        });
    }

    private void styleTextField(TextField field) {
        field.setStyle(
                "-fx-padding: 10; " +
                        "-fx-border-color: #d1d5db; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-font-size: 14;"
        );
        field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                field.setStyle(
                        "-fx-padding: 10; " +
                                "-fx-border-color: #38bdf8; " +
                                "-fx-border-width: 2; " +
                                "-fx-border-radius: 6; " +
                                "-fx-background-radius: 6; " +
                                "-fx-font-size: 14;"
                );
            } else {
                field.setStyle(
                        "-fx-padding: 10; " +
                                "-fx-border-color: #d1d5db; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 6; " +
                                "-fx-background-radius: 6; " +
                                "-fx-font-size: 14;"
                );
            }
        });
    }

    private void styleTextArea(TextArea area) {
        area.setStyle(
                "-fx-padding: 10; " +
                        "-fx-border-color: #d1d5db; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 6; " +
                        "-fx-background-radius: 6; " +
                        "-fx-font-size: 14;"
        );
        area.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                area.setStyle(
                        "-fx-padding: 10; " +
                                "-fx-border-color: #38bdf8; " +
                                "-fx-border-width: 2; " +
                                "-fx-border-radius: 6; " +
                                "-fx-background-radius: 6; " +
                                "-fx-font-size: 14;"
                );
            } else {
                area.setStyle(
                        "-fx-padding: 10; " +
                                "-fx-border-color: #d1d5db; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 6; " +
                                "-fx-background-radius: 6; " +
                                "-fx-font-size: 14;"
                );
            }
        });
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}