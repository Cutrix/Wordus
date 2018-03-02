import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App_main extends Application{

	Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Word_Us");
        stage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
	
}
