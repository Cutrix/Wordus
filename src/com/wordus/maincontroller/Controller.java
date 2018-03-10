package com.wordus.maincontroller;

import com.wordus.essentials.Dico;
import com.wordus.essentials.SpellChecker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import javafx.event.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javafx.stage.DirectoryChooser;
import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Controller {

    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;
    private TextInputDialog textInputDialog;
    private Stage stage;

    private Dico dc = new Dico();
    private SpellChecker sp;

    private Text text;
    private Label lb;

    private final static int SEE_FROM = 3;


    // Menu gestion de saisi
    //Nouveau
    @FXML
    TabPane Tabpaner = new TabPane();
    int numTab = 2;

    @FXML
    private void NouveauTab() {
        Tab tab1 = new Tab("Page " + numTab);
        Tabpaner.getTabs().addAll(tab1);
        HTMLEditor htmlEditor1 = new HTMLEditor();
        tab1.setContent(htmlEditor1);
        numTab = numTab + 1;
        Document doc = Jsoup.parse(htmlEditor.getHtmlText());
        Element content = doc.tagName("body p");
        System.out.println(content.text());
    }

    //Ouvrir fichier

    File file;

    @FXML
    private void OuvrirFichier() {
        System.out.println(numTab);
        Tab tab1 = new Tab("Page " + numTab);
        Tabpaner.getTabs().addAll(tab1);
      //  HTMLEditor htmlEditor0 = new HTMLEditor();
        tab1.setContent(htmlEditor);
        numTab = numTab + 1;

        FileChooser fileChooser = new FileChooser();

//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("AVI files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

//Show open file dialog
        file = fileChooser.showOpenDialog(null);
        String contenu = file.toString();
        System.out.println(contenu);
        //htmlEditor0.setHtmlText(FileChooser);
    }



    //Enregistrer un doc
    @FXML
    private void EnregistFile() {
        FileChooser fileChooser = new FileChooser();

//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

//Show save file dialog
        File file = fileChooser.showSaveDialog(stage);

        // FileChooser fileChooser = new FileChooser();
        // URI yourPics = selectedFile.getAbsoluteFile().toURI();

        if(file != null){
            Document doc = Jsoup.parseBodyFragment(htmlEditor.getHtmlText());
            Element body = doc.body();
            SaveFile(String.valueOf(body), file);
         //SaveFile(htmlEditor.getHtmlText(), file);

            //System.out.println(content);
        }
    }
@FXML
private void SaveFile(String content, File file){
    try {
        FileWriter fileWriter = null;

        fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    } catch (IOException ex) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
    }

}

@FXML
private void tester() {
     //System.out.println("je suis la ");
        //System.out.println(htmlEditor.getHtmlText());
        /*Document doc = Jsoup.parse(htmlEditor.getHtmlText());
        Element content = doc.tagName("body p");
        System.out.println(content.outerHtml());*/
        Document doc = Jsoup.parseBodyFragment(htmlEditor.getHtmlText());
        Element body = doc.body();
        System.out.println(body);
}



    //Quiter l'application
    @FXML
    private void quitter() {
        System.exit(0);
    }

    @FXML
    private void getAera() {
        //statusLbl.setText(htmlEditor.getHtmlText());
    }

    //Code pour le dictionnaire
    @FXML
    private void setLanguageFr() {
        dc = new Dico("Fr", "dico_fr.txt");
        statusLbl.setText(dc.getLang());
    }

    @FXML
    private void setLanguageAng() {
        dc = new Dico("En", "dico_ang.txt");
        statusLbl.setText(dc.getLang());
    }

    @FXML
    private void ajoutMot() {
        textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Ajout dictionnaire");
        textInputDialog.setHeaderText("Ajouter dans le dictionnaire");

        Optional<String> mot = textInputDialog.showAndWait();
        if (mot.isPresent()) {
            dc.add(mot.get());
            statusLbl.setText("Mot Ajoute");
        }
    }

    @FXML
    private void delWord() {
        textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Suppression du dictionnaire");
        textInputDialog.setHeaderText("Supprimer dans le dictionnaire");

        Optional<String> mot = textInputDialog.showAndWait();
        if (mot.isPresent()) {
            dc.del(mot.get());
            statusLbl.setText("Mot supprimer");
        }
    }

    @FXML
    private void updateWord() {
        dc.update("jouer", "boire");
    }

    @FXML
    private void voirmots() {
        //dc.getWords();
        dc.del("dfd");
    }

    @FXML
    private void insertImg() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        URI yourPics = selectedFile.getAbsoluteFile().toURI();

        Document doc = Jsoup.parse(htmlEditor.getHtmlText());
        Element content = doc.tagName("body p");

        htmlEditor.setHtmlText(content.text() + "<img src='"+yourPics+"' style='width:300px; height: 300px'/>");
    }

    @FXML
    public void checkSyntax() {
        sp = new SpellChecker(dc);
        htmlEditor.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                String htmlText  = htmlEditor.getHtmlText();
                Document doc = Jsoup.parse(htmlEditor.getHtmlText());
                Element content = doc.tagName("body p");
                ArrayList<String> toCompares = new ArrayList<String>(Arrays.asList(content.text().split(" ")));
                int lastWordIndex = toCompares.size();
                String lastWordForLikely;
                String lastWord = toCompares.get(lastWordIndex-1);
                if (lastWord.length() > SEE_FROM) {
                    lastWordForLikely = toCompares.get(lastWordIndex-1).substring(0,3);
                } else {
                    lastWordForLikely = toCompares.get(lastWordIndex-1).substring(0,2);
                }
                System.out.println(sp.getWordsStartBy(lastWordForLikely));
                //System.out.println(htmlEditor.getHtmlText());
                //htmlEditor.setHtmlText("<p style='text-decoration: red underline wavy ;'>"+lastWord+"</p>");
                //System.out.println("------------"+htmlEditor.getHtmlText());
                //System.out.print(lastWord);
            }
        });
    }
}
