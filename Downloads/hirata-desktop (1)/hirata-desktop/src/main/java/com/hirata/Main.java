package com.hirata;

import com.hirata.util.SceneManager;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);

        stage.getIcons().add(
                new Image(getClass().getResourceAsStream("/view/logo.png"))
        );

        SceneManager.loadScene("/view/login.fxml", "Hirata - Login", 500, 650);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
