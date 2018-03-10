import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class App_main extends Application{
	Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));

        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Wordus");
		stage.show();
		//icon
//		stage.getIcons().add(new Image("font\\icon\\text-editor.png"));
		//HtmlEditor css
		//stage.getStylesheets().add(getClass().getResource("htmleditor.css").toExternalForm());


		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
	
}
