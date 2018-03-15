package com.wordus.controller;

import com.wordus.essentials.Dico;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDicoWordController implements Initializable {

    public TextField tfOldWord;
    public TextField tfNewWord;
    public Button modifierMot;

    public Dico dc= new Dico();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void updateWord(ActionEvent actionEvent) {

       //dc.update(tfOldWord.getCharacters(), tfNewWord.getCharacters());
    }
}
