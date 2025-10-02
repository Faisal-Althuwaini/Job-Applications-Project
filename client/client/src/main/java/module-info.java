module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.desktop;

    opens com.example.client to javafx.fxml;
    opens com.example.client.models to com.fasterxml.jackson.databind;
    opens com.example.client.view to javafx.fxml;

    exports com.example.client;
    exports com.example.client.models;
    exports com.example.client.view;
    exports com.example.client.services;
    exports com.example.client.util;
}