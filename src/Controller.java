import javafx.fxml.FXML;

public class Controller {
    String langue = "fr";
    //Fonction de selection de la langue
    @FXML
    private void selection_lang() {
        System.out.print(langue);
    }
}
