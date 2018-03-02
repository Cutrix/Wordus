import com.wordus.lang.Language;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.web.HTMLEditor;


public class Controller {

    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;
    Language lang = new Language();

    @FXML
    private void quitter() {
        System.exit(0);
    }

    @FXML
    private void getAera() {
        statusLbl.setText(htmlEditor.getHtmlText());
    }

    @FXML
    private void setImage() {
        Image image;
    }

    @FXML
    private void setLanguageFr() {
        lang.setLanguage("fr");
        statusLbl.setText(lang.getLanguage());
    }

    @FXML
    private void setLanguageAng(){
        lang.setLanguage("ang");
        statusLbl.setText(lang.getLanguage());
    }
}
