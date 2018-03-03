import com.wordus.essentials.Dico;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.web.HTMLEditor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;


public class Controller {

    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;
    private TextInputDialog textInputDialog;
    private String dico;


    Dico dc = new Dico();

    @FXML
    private void quitter() {
        System.exit(0);
    }

    @FXML
    private void getAera() {
        //statusLbl.setText(htmlEditor.getHtmlText());
        statusLbl.setText(dc.getDictionnary());
    }

    @FXML
    private void setImage() {
        Image image;
    }

    private void defineDico() {
        dc.setDictionnary(dc.getLanguage());
        this.dico =  dc.getDictionnary();
        System.out.println(dc.getDictionnary());
    }


    //Code pour le dictionnaire
    @FXML
    private void setLanguageFr() {
        dc.setLanguage("Francais");
        statusLbl.setText(dc.getLanguage());
        defineDico();
    }

    @FXML
    private void setLanguageAng(){
        dc.setLanguage("Anglais");
        statusLbl.setText(dc.getLanguage());
        defineDico();
    }

    public void ajoutMot(ActionEvent actionEvent) throws IOException {
        textInputDialog = new TextInputDialog("Test");
        textInputDialog.setTitle("Ajout dictionnaire");
        textInputDialog.setHeaderText("Ajout de nouveau mot dans le dictionnaire");
        textInputDialog.setContentText("le mot: ");

        Optional<String> mot = textInputDialog.showAndWait();
        if (mot.isPresent()) {
            dc.add(mot.get());
        }

    }

    public String getDico() {
        return this.dico;
    }
}
