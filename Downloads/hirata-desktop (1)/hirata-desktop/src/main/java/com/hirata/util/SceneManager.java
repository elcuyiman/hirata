package com.hirata.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage stage;
    private static boolean initialized = false;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
    }

    public static void loadScene(String fxmlPath, String title, double width, double height) {
        try {
            boolean wasMaximized = stage.isMaximized();
            boolean wasFullScreen = stage.isFullScreen();

            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            stage.setTitle(title);
            stage.setScene(scene);

            // Solo aplicar tamaño inicial una vez
            if (!initialized) {
                stage.setWidth(width);
                stage.setHeight(height);
                stage.centerOnScreen();
                initialized = true;
            }

            // Restaurar estado anterior
            stage.setMaximized(wasMaximized);
            stage.setFullScreen(wasFullScreen);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}