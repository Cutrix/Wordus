package com.wordus.essentials;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Modal {

    public static void showMdalRessource(String res, String title) {
        try {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);
        stage.getIcons().add(new Image("font/icon/text-editor.png"));

        FXMLLoader loader = new FXMLLoader(Modal.class.getResource(res));

            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
