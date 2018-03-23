package com.wordus.controller;

import com.wordus.essentials.Dico;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDicoWordController implements Initializable {

    public TextField tfOldWord;
    public TextField tfNewWord;
    public Button modifierMot;

    public Dico dc= new Dico();
    public AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TextFields.bindAutoCompletion(tfOldWord, dc.getWords().split(" "));

    }


    public void updateWord(ActionEvent actionEvent) {

       String oldWord = tfOldWord.getCharacters().toString();
       String newWord = tfNewWord.getCharacters().toString();

       dc.update(oldWord, newWord);

        Window stage;
        root.getScene().getWindow().hide();
    }
}
