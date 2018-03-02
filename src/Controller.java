import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label statusLbl;

    //Fonction de selection de la langue
    @FXML
    private void selection_lang() {
        statusLbl.setText("Hello World");
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }
}
