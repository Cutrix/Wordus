package com.wordus.controller;


import com.wordus.essentials.AlertBox;
import com.wordus.essentials.Dico;
import com.wordus.essentials.Modal;
import com.wordus.essentials.SpellChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.w3c.dom.DOMImplementation;

import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller implements Initializable{


    @FXML
    public BorderPane borderPane;
    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;
    private TextInputDialog textInputDialog;
    private ContextMenu contextMenu;

    Stage stage;

    public Dico dc = new Dico();
    private SpellChecker sp;

    private Text text;
    private Label lb;
    private Tab currentTabs;

    private final static int SEE_FROM = 3;

    private HTMLEditor htmlEditor1;
    private Tab pan;


    // Menu gestion de saisi
    //Nouveau
    @FXML
    TabPane Tabpaner = new TabPane();
    int numTab = 2;
    int nb = 0;
    @FXML
    Object[] tableau1 = new Object[5];
    Object[] tableau2 = new Object[5];
    int j = 0;
    int i = 0;
    @FXML
    public void nameHTML(String eer) {
         eer = "abc";
    }

    @FXML
    public void NouveauTab() {
        Tab tab1 = new Tab("Page " + numTab);
        HTMLEditor page1 = new HTMLEditor();
        Tabpaner.getTabs().addAll(tab1);
        tab1.setContent(page1);
        numTab = numTab + 1;
        //tab1.setStyle("-fx-background-color: #e6e6e6");

        page1.getStylesheets().add(getClass().getResource("/css/htmlEditcss/htmleditor.css").toExternalForm());
        Tabpaner.getStylesheets().add(getClass().getResource("/css/tabePane/tabPane.css").toExternalForm());


        page1.setPrefWidth(1323);
        page1.setPrefHeight(200);


        tableau1[i] = page1;
        System.out.println(tableau1[i]);

        tableau2[j] = tab1.getText();
        System.out.println(tableau2[j]);
        i++;
        j++;


    }

    @FXML
            private void Tabcolor(){
        currentTabs.setStyle("-fx-background-color: #331a80");
    }
    //Ouvrir fichier

    File file;

    @FXML
    private void OuvrirFichier() {

        /*htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();
        FileChooser fileChooser = new FileChooser();

//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("AVI files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

//Show open file dialog
        file = fileChooser.showOpenDialog(null);
        String contenu = file.toString();
        System.out.println(contenu);*/
        //htmlEditor0.setHtmlText(FileChooser);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            try {
                InputStream flux = new FileInputStream(selectedFile.getAbsoluteFile().toString());
                InputStreamReader lecture = new InputStreamReader(flux);
                BufferedReader buff = new BufferedReader(lecture);
                String line;
                String output = null;

                while ((line = buff.readLine()) != null) {
                    output += line;
                }
                htmlEditor.setHtmlText(output);
                currentTabs.getContent().getParent();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @FXML
    private void EnregistFile(){

    }

    @FXML
    public void Apros(){
        String  s="MEMBRES DU GROUPE:          BIDI PAUL      BOUA LEANDRE     DIBI BRICE      KOFFI SANDRA        YESSO LINDA     ZIE ALASANE";
        JOptionPane.showMessageDialog(null,s);
    }


    //Enregistrer un doc
    @FXML
    private void EnregistsreSous() {

        currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());


        htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();
        System.out.println(htmlEditor1.getHtmlText());

        FileChooser fileChooser = new FileChooser();

//Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

//Show save file dialog
        File file = fileChooser.showSaveDialog(stage);


        if (file != null) {
            Document doc = Jsoup.parseBodyFragment(htmlEditor1.getHtmlText());
            // Element body = doc.body();
            Element content = doc.tagName("body p");
            SaveFile(String.valueOf(content.text()), file);

        }
    }

    @FXML
    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        dc = new Dico("Langue Française selectionée !", "dico_fr.txt");
        statusLbl.setText(dc.getLang());
    }

    @FXML
    private void setLanguageAng() {
        dc = new Dico("Langue Anglaise selectionée !", "dico_ang.txt");
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
        //dc.update("jouer", "boire");
        Modal.showMdalRessource("../../../fxml/UpdateDicoWord.fxml");
    }


    @FXML
    private void insertImg() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        URI yourPics = selectedFile.getAbsoluteFile().toURI();

        Document doc = Jsoup.parse(htmlEditor.getHtmlText());
        Element content = doc.tagName("body p");

        htmlEditor.setHtmlText(content.text() + "<img src='" + yourPics + "' style='width:300px; height: 300px'/>");
    }


    @FXML
    public void checkSyntax() {

        sp = new SpellChecker(dc);
        htmlEditor.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                String htmlText = htmlEditor.getHtmlText();
                Document doc = Jsoup.parse(htmlEditor.getHtmlText());
                Element content = doc.tagName("body p");
                ArrayList<String> toCompares = new ArrayList<String>(Arrays.asList(content.text().split(" ")));
                int lastWordIndex = toCompares.size();
                String lastWordForLikely;
                String lastWord = toCompares.get(lastWordIndex - 1);

                if (lastWord.length() > SEE_FROM) {
                    lastWordForLikely = toCompares.get(lastWordIndex - 1).substring(0, 3);
                } else {
                    if (lastWord.length() == 1) lastWordForLikely = toCompares.get(lastWordIndex - 1).substring(0, 1);
                    else lastWordForLikely = toCompares.get(lastWordIndex - 1).substring(0, 2);
                }

                ContextMenu cm = new ContextMenu();

                //Les mots dans le dico en accord avec le mot
                String likelyWords = sp.getWordsStartBy(lastWordForLikely);

                //On rend les mots en ArrayList
                ArrayList<String> likelyAlWrods = new ArrayList<String>(Arrays.asList(likelyWords.split(" ")));

                //System.out.println(likelyAlWrods.size());
                //int lengthLikelyAlWords = likelyAlWrods.size()-1;

                ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

                for (int i = 0; i < likelyAlWrods.size(); i++) {
                    //System.out.println(likelyAlWrods.get(i));
                    menuItems.add(new MenuItem(likelyAlWrods.get(i)));
                    int finalI = i;
                    menuItems.get(i).setOnAction(event1 -> {
                        //System.out.println(likelyAlWrods.get(finalI));
                        String replaceByLikelyWord = htmlEditor.getHtmlText().replace(lastWord, likelyAlWrods.get(finalI));
                        htmlEditor.setHtmlText(replaceByLikelyWord);
                        //System.out.println(htmlEditor.getHtmlText());

                    });
                }
                cm.getItems().addAll(menuItems);
                htmlEditor.setContextMenu(cm);
            }
        });
    }


    public void voirmots(ActionEvent actionEvent) throws IOException {
        /*try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Dictionnaire.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            pane.autosize();
            borderPane.setCenter(pane);

            statusLbl.setText("Votre Dictionnaire personnel");

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Modal.showMdalRessource("../../../fxml/Dictionnaire.fxml");
    }

    public void search(ActionEvent actionEvent) {
        textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Rechercher");
        textInputDialog.setHeaderText("Rechercher un mot");
        Optional<String> match = textInputDialog.showAndWait();
        if (match.isPresent()) {
            String replaceWordsByMatchesWords = htmlEditor.getHtmlText().replace(match.get(), "<span style='background-color: yellow'> " + match.get() + "</span> ");
            htmlEditor.setHtmlText(replaceWordsByMatchesWords);
        }
    }

    public Tab getCurrentTabs() {
        return currentTabs;
    }


    public void Test(ActionEvent actionEvent) {
        //LEssentials.init()
    }

    //GESTION DE LA BASE DE DONNEE
    @FXML
    private void createTable() {
        AlertBox.displayCreateTable();
    }

    @FXML
    private void updateTable() {
        AlertBox.displayCreateTable();
    }

    @FXML
    private void dropTable() {
        AlertBox.displayDropTable();
    }

    @FXML
    private void selectData() {
        AlertBox.displaySelectData();
    }

    @FXML
    private void insertData() {
        AlertBox.displayInsertData();
    }

    @FXML
    private void deleteData() {
        AlertBox.displayDeleteData();
    }

    @FXML
    private void actifcolor() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}