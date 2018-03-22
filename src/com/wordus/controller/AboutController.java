package com.wordus.controller;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    public AnchorPane root;
    public Text txtAbout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtAbout.setText("Wordus, un editeur simple et leger a utiliser \n v1.1");
    }
}
