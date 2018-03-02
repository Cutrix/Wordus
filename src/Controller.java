import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;

public class Controller {

    @FXML
    private Label statusLbl;
    @FXML
    private HTMLEditor htmlEditor;

    //Fonction de selection de la langue
    @FXML
    private void selection_lang() {
        statusLbl.setText("Hello World");
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }

    @FXML
    private void getAera() {
        statusLbl.setText(htmlEditor.getHtmlText());
    }
}
