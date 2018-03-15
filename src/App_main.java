import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;


public class App_main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Graph.fxml"));

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Wordus");
		primaryStage.show();
		//icon
		//primaryStage.getIcons().add(new Image("font\\icon\\text-editor.png"));
		primaryStage.getIcons().add(new Image("font/icon/text-editor.png"));


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

