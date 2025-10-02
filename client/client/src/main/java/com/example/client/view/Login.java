package com.example.client.view;
import com.example.client.models.AuthResponse;
import com.example.client.services.ApiService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    private Stage stage;
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label errorLabel;
    private ApiService apiService = ApiService.getInstance();

    public Login(Stage stage) {
        this.stage = stage;
    }

    public Scene createScene() {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0f9ff, #dbeafe);");

        VBox loginBox = new VBox(20);
        loginBox.setAlignment(Pos.CENTER);
        loginBox.setMaxWidth(400);
        loginBox.setPadding(new Insets(40));
        loginBox.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 20, 0, 0, 10);"
        );

        Label titleLabel = new Label("Welcome Back");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 32));
        titleLabel.setStyle("-fx-text-fill: linear-gradient(to right, #38bdf8, #3b82f6);");

        Label subtitleLabel = new Label("Sign in to your account");
        subtitleLabel.setFont(Font.font("System", FontWeight.NORMAL, 14));
        subtitleLabel.setTextFill(Color.web("#6b7280"));

        errorLabel = new Label();
        errorLabel.setStyle(
                "-fx-background-color: #fef2f2; " +
                        "-fx-border-color: #fecaca; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 12; " +
                        "-fx-text-fill: #dc2626; " +
                        "-fx-font-size: 12;"
        );
        errorLabel.setVisible(false);
        errorLabel.setWrapText(true);
        errorLabel.setMaxWidth(320);

        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setMaxWidth(320);

        Label emailLabel = new Label("Email Address");
        emailLabel.setFont(Font.font("System", FontWeight.SEMI_BOLD, 12));
        emailLabel.setTextFill(Color.web("#374151"));

        emailField = new TextField();
        emailField.setPromptText("Enter your email");
        emailField.setPrefWidth(320);
        emailField.setPrefHeight(45);
        emailField.setStyle(
                "-fx-padding: 12; " +
                        "-fx-border-color: #d1d5db; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-font-size: 14; " +
                        "-fx-background-color: white;"
        );
        emailField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                emailField.setStyle(
                        "-fx-padding: 12; " +
                                "-fx-border-color: #38bdf8; " +
                                "-fx-border-width: 2; " +
                                "-fx-border-radius: 8; " +
                                "-fx-background-radius: 8; " +
                                "-fx-font-size: 14; " +
                                "-fx-background-color: white;"
                );
            } else {
                emailField.setStyle(
                        "-fx-padding: 12; " +
                                "-fx-border-color: #d1d5db; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 8; " +
                                "-fx-background-radius: 8; " +
                                "-fx-font-size: 14; " +
                                "-fx-background-color: white;"
                );
            }
        });

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("System", FontWeight.SEMI_BOLD, 12));
        passwordLabel.setTextFill(Color.web("#374151"));

        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(320);
        passwordField.setPrefHeight(45);
        passwordField.setStyle(
                "-fx-padding: 12; " +
                        "-fx-border-color: #d1d5db; " +
                        "-fx-border-width: 1; " +
                        "-fx-border-radius: 8; " +
                        "-fx-background-radius: 8; " +
                        "-fx-font-size: 14; " +
                        "-fx-background-color: white;"
        );
        passwordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                passwordField.setStyle(
                        "-fx-padding: 12; " +
                                "-fx-border-color: #38bdf8; " +
                                "-fx-border-width: 2; " +
                                "-fx-border-radius: 8; " +
                                "-fx-background-radius: 8; " +
                                "-fx-font-size: 14; " +
                                "-fx-background-color: white;"
                );
            } else {
                passwordField.setStyle(
                        "-fx-padding: 12; " +
                                "-fx-border-color: #d1d5db; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 8; " +
                                "-fx-background-radius: 8; " +
                                "-fx-font-size: 14; " +
                                "-fx-background-color: white;"
                );
            }
        });

        loginButton = new Button("Sign In");
        loginButton.setPrefWidth(320);
        loginButton.setPrefHeight(45);
        loginButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #38bdf8, #3b82f6); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14; " +
                        "-fx-font-weight: bold; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-effect: dropshadow(gaussian, rgba(56,189,248,0.3), 10, 0, 0, 5);"
        );
        loginButton.setOnMouseEntered(e -> {
            loginButton.setStyle(
                    "-fx-background-color: linear-gradient(to right, #0ea5e9, #2563eb); " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14; " +
                            "-fx-font-weight: bold; " +
                            "-fx-cursor: hand; " +
                            "-fx-background-radius: 8; " +
                            "-fx-effect: dropshadow(gaussian, rgba(56,189,248,0.5), 15, 0, 0, 8);" +
                            "-fx-scale-x: 1.02; " +
                            "-fx-scale-y: 1.02;"
            );
        });
        loginButton.setOnMouseExited(e -> {
            loginButton.setStyle(
                    "-fx-background-color: linear-gradient(to right, #38bdf8, #3b82f6); " +
                            "-fx-text-fill: white; " +
                            "-fx-font-size: 14; " +
                            "-fx-font-weight: bold; " +
                            "-fx-cursor: hand; " +
                            "-fx-background-radius: 8; " +
                            "-fx-effect: dropshadow(gaussian, rgba(56,189,248,0.3), 10, 0, 0, 5);"
            );
        });
        loginButton.setOnAction(e -> handleLogin());

        formBox.getChildren().addAll(
                emailLabel, emailField,
                passwordLabel, passwordField
        );

        loginBox.getChildren().addAll(
                titleLabel,
                subtitleLabel,
                errorLabel,
                formBox,
                loginButton
        );

        root.getChildren().add(loginBox);

        return new Scene(root, 500, 650);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        errorLabel.setVisible(false);

        if (email.isEmpty() || password.isEmpty()) {
            showError("Please enter email and password");
            return;
        }

        loginButton.setDisable(true);
        loginButton.setText("Signing In...");

        new Thread(() -> {
            try {
                AuthResponse authResponse = apiService.login(email, password);

                javafx.application.Platform.runLater(() -> {
                    System.out.println("=== LOGIN DEBUG ===");
                    System.out.println("User Name: " + authResponse.getUser().getName());
                    System.out.println("User Email: " + authResponse.getUser().getEmail());
                    System.out.println("User Role: '" + authResponse.getUser().getRole() + "'");
                    System.out.println("==================");

                    String userRole = authResponse.getUser().getRole();
                    if (userRole == null || !userRole.equals("ADMIN")) {
                        showError("Access denied. Admin only. Your role: '" + userRole + "'");
                        resetLoginButton();
                    } else {
                        navigateToDashboard();
                    }
                });

            } catch (IOException e) {
                javafx.application.Platform.runLater(() -> {
                    showError("Login failed: " + e.getMessage());
                    resetLoginButton();
                });
            }
        }).start();
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    private void resetLoginButton() {
        loginButton.setDisable(false);
        loginButton.setText("Sign In");
    }

    private void navigateToDashboard() {
        DashBoard dashboardView = new DashBoard(stage);
        Scene dashboardScene = dashboardView.createScene();
        stage.setScene(dashboardScene);
        stage.setTitle("Admin Dashboard - HIRE");
        stage.setMaximized(true);
    }
}