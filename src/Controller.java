import com.wordus.essentials.Dico;
import com.wordus.essentials.SpellChecker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class Controller {

    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;
    private TextInputDialog textInputDialog;
    private Stage stage;

    private Dico dc = new Dico();
    private SpellChecker sp;


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
        System.out.println(selectedFile.getAbsoluteFile().toURI());
    }

    @FXML
    private void checkSyntax() {
        sp = new SpellChecker(dc);
        System.out.print(sp.getWordsStartBy("boi"));
    }
}
