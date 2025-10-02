package com.example.client.util;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Mediator {
    private final BorderPane root;
    private final Map<String, Node> viewCache = new HashMap<>();
    private final Map<String, List<Consumer<String>>> listeners = new HashMap<>();

    public Mediator(BorderPane root) {
        this.root = root;
    }

    public void registerView(String viewName, Node view) {
        viewCache.put(viewName, view);
    }

    public void switchView(String viewName) {
        Node view = viewCache.get(viewName);
        if (view != null) {
            root.setCenter(view);
        } else {
            System.err.println("View not registered: " + viewName);
        }
    }

    public void addListener(String eventType, Consumer<String> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public void notifyListeners(String eventType, String data) {
        List<Consumer<String>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            for (Consumer<String> listener : eventListeners) {
                listener.accept(data);
            }
        }
    }
}

