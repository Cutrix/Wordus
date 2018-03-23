package com.wordus.controller;


import com.wordus.essentials.AlertBox;
import com.wordus.essentials.Dico;
import com.wordus.essentials.Modal;
import com.wordus.essentials.SpellChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    public Pane root;
    @FXML
    public BorderPane borderPane;
    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;
    private TextInputDialog textInputDialog;


    private String pathForRegister;

    Stage stage;

    public Dico dc = new Dico();
    private SpellChecker sp;


    private Tab currentTabs;

    private final static int SEE_FROM = 3;

    private HTMLEditor htmlEditor1;
    private Tab pan;

    private boolean isSave = false;


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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All files", "*"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            try {
                InputStream flux = new FileInputStream(selectedFile.getAbsoluteFile().toString());
                InputStreamReader lecture = new InputStreamReader(flux);
                BufferedReader buff = new BufferedReader(lecture);
                String line;
                String output = "";

                while ((line = buff.readLine()) != null) {
                    output += line;
                }
                currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());


                htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();

                htmlEditor1.setHtmlText(output);

                System.out.println(output);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @FXML
    public void Apros(){


       // Modal.showMdalRessource("../../../fxml/Apropos.fxml", "A propos");
try {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/Apropos.fxml"));
    Scene scene = new Scene(root);

    Stage stage = new Stage();
    stage.getIcons().add(new Image("font/icon/text-editor.png"));
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setScene(scene);
    stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }

    }


    //Enregistrer un doc

    @FXML
    private void EnregistFile(){
        if (isSave()) {

            //System.out.println(this.getPathForRegister());
           // SaveFile(htmlEditor1.getHtmlText(), new File(this.getPathForRegister().toString()));
            Document doc = Jsoup.parseBodyFragment(htmlEditor1.getHtmlText());
            Element content = doc.tagName("body p");
            SaveFile(String.valueOf(content.text()), new File(this.getPathForRegister().toString()));
        }
        else {
            currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());
            htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();
            System.out.println(htmlEditor1.getHtmlText());

            FileChooser fileChooser = new FileChooser();

//Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

//Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            this.setPathForRegister(file.toString());



            if (file != null) {
                Document doc = Jsoup.parseBodyFragment(htmlEditor1.getHtmlText());
                // Element body = doc.body();
                Element content = doc.tagName("body p");
                SaveFile(String.valueOf(content.text()), file);
                // SaveFile(htmlEditor1.getHtmlText(), file);

                setIsSave(true);
            }
        }


    }


    @FXML
    public void EnregistsreSous() {

        //boolean saved = false;
        isSave = false;

        if (!isSave()) {
            currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());
            htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();
            //System.out.println(htmlEditor1.getHtmlText());

            FileChooser fileChooser = new FileChooser();

//Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

//Show save file dialog
            File file = fileChooser.showSaveDialog(stage);

            this.setPathForRegister(file.toString());



            if (file != null) {
                Document doc = Jsoup.parseBodyFragment(htmlEditor1.getHtmlText());
                 Element body = doc.body();
                //Element content = doc.tagName("body p");
                //SaveFile(String.valueOf(content.text()), file);
                SaveFile(String.valueOf(body), file);


               setIsSave(true);
            }
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


    //Code pour le dictionnaire
    @FXML
    private void setLanguageFr() {
        dc = new Dico("Fr", "dico_fr.txt");
        sp = new SpellChecker(dc);
        statusLbl.setText("Langue Fancaise selectionnée");
    }

    @FXML
    private void setLanguageAng() {
        dc = new Dico("En", "dico_ang.txt");
        sp = new SpellChecker(dc);
        statusLbl.setText("Langue Anglaise selectionnée");
    }

    @FXML
    private void ajoutMot() {
        textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Dictionnaire");
        textInputDialog.setHeaderText("Ajouter dans le dictionnaire");

        // Get the Stage.
        Stage stage1 = (Stage) textInputDialog.getDialogPane().getScene().getWindow();

// Add a custom icon.
        //stage1.getIcons().add(new Image(this.getClass().getResource("font/icon/text-editor.png").toString()));
       stage1.getIcons().add(new Image("font/icon/text-editor.png"));


        Optional<String> mot = textInputDialog.showAndWait();
        if (mot.isPresent()) {
            dc.add(mot.get());
            statusLbl.setText("Mot ajouté");
        }
    }

    @FXML
    private void delWord() {
        textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Dictionnaire");
        textInputDialog.setHeaderText("Supprimer dans le dictionnaire");
        Stage stage1 = (Stage) textInputDialog.getDialogPane().getScene().getWindow();

// Add a custom icon.
        //stage1.getIcons().add(new Image(this.getClass().getResource("font/icon/text-editor.png").toString()));
        stage1.getIcons().add(new Image("font/icon/text-editor.png"));

        //Autocompletion du textField

        TextField tf = textInputDialog.getEditor();
        TextFields.bindAutoCompletion(tf, dc.getWords().split(" "));

        Optional<String> mot = textInputDialog.showAndWait();
        if (mot.isPresent()) {
            dc.del(mot.get());
            statusLbl.setText("Mot supprimer");
        }
    }

    @FXML
    private void updateWord() {
        //dc.update("jouer", "boire");

        Modal.showMdalRessource("../../../fxml/UpdateDicoWord.fxml", "Modification des mots du dictionnaire");
    }


    @FXML
    private void insertImg() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Votre Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        URI yourPics = selectedFile.getAbsoluteFile().toURI();

        Document doc = Jsoup.parse(htmlEditor.getHtmlText());
        Element content = doc.tagName("body p");
        currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());
        htmlEditor = (HTMLEditor) getCurrentTabs().getContent();

        currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());

        htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();

        htmlEditor1.setHtmlText(content.text() + "<img src='" + yourPics + "' style='width:300px; height: 300px'/>");
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
        statusLbl.setText("Dictionnaire");

        Modal.showMdalRessource("../../../fxml/Dictionnaire.fxml", "Mots du dictionnaire");
        statusLbl.setText("Bienvenue sur Wordus Texte Editeur");
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

    public boolean isSave() {
        return isSave;
    }

    public void setIsSave(boolean e) {
        this.isSave = e;
    }


    public void Test(ActionEvent actionEvent) {
        /*currentTabs = Tabpaner.getTabs().get(Tabpaner.getSelectionModel().getSelectedIndex());
        htmlEditor1 = (HTMLEditor) getCurrentTabs().getContent();
        htmlEditor1.getProperties().addListener((InvalidationListener) observable -> System.out.println("Champ d'édition modifié !"));*/

        System.out.println(isSave());

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

    public void searchReplace(ActionEvent actionEvent) {
        Modal.showMdalRessource("../../../fxml/ReplaceWord.fxml", "Rechercher/Remplacer");
    }

    public String getPathForRegister() {
        return pathForRegister;
    }

    public void setPathForRegister(String pathForRegister) {
        this.pathForRegister = pathForRegister;
    }

    @FXML
    private void BackMain() {

        root.getScene().getWindow().hide();
    }
}