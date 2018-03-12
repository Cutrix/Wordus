import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class App_main extends Application{

	@FXML
	public Stage win;
	public Parent root;

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {

			win = primaryStage;
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("Graph.fxml"));

        root = FXMLLoader.load(getClass().getResource("Graph.fxml"));

        win.setScene(new Scene(root));
        win.setTitle("Wordus");
		win.show();
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
