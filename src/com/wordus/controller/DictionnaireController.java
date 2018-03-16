package com.wordus.controller;

import com.wordus.essentials.Dico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class DictionnaireController implements Initializable{
    public Tab frTab;
    public AnchorPane rootPane;
    public BorderPane myPane;
    public Tab enTab;
    public ListView enLv;
    public ListView frLv;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Dico dc = new Dico();
        dc.setLang("Fr");
        ArrayList<String> alWordsFr = new ArrayList<String>(Arrays.asList(dc.getWords().split(" ")));
        ObservableList<String> namesFr = FXCollections.observableArrayList(alWordsFr);
        frLv.setItems(namesFr);
        Dico dcAn = new Dico("En", "dico_ang.txt");
        ArrayList<String> alWordsAn = new ArrayList<String>(Arrays.asList(dcAn.getWords().split(" ")));
        ObservableList<String> names = FXCollections.observableArrayList(alWordsAn);
        enLv.setItems(names);


    }
    


    public void back(ActionEvent actionEvent) {

        try {
            Parent myWordus = FXMLLoader.load(getClass().getResource("/fxml/Graph.fxml"));
            Scene sceneWordus = new Scene(myWordus);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.setScene(sceneWordus);
            app_stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
