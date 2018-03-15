package com.wordus.essentials;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Modal {

    public static void showMdalRessource(String res) {
        try {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modification des mots du dico");
        stage.setMinWidth(250);

        FXMLLoader loader = new FXMLLoader(Modal.class.getResource(res));

            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
