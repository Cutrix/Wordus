import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class App extends Application{

	@Override
	public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("wordus.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Wordus");
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
