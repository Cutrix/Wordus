package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.application.Platform;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/*Ouattara Zié Alhassane*/

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Interface principale
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,600, Color.RED);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			//creation du menu
			MenuBar menuBar = new MenuBar();
			    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
			    root.setTop(menuBar);
			  // File menu - new, save, exit
		    Menu fileMenu = new Menu("Gestion de saisie");
		    MenuItem newMenuItem = new MenuItem("New");
		    MenuItem openMenuItem = new MenuItem("Open file");
		    MenuItem saveMenuItem = new MenuItem("Save");
		    MenuItem exitMenuItem = new MenuItem("Exit");
		    exitMenuItem.setOnAction(actionEvent -> Platform.exit());
            //menu edition
		    Menu editMenu = new Menu("Edit");
		    MenuItem copieMenuItem = new MenuItem(" Copier      ");
		    MenuItem couperMenuItem = new MenuItem("Couper    ");
            MenuItem collerMenuItem = new MenuItem("Coller     ");
            MenuItem selectionnerMenuItem = new MenuItem("Selectionner");
            MenuItem taileMenuItem = new MenuItem("Taille");
            MenuItem couleurMenuItem = new MenuItem("Couleur");

            //menu
		    fileMenu.getItems().addAll(newMenuItem, saveMenuItem, openMenuItem, new SeparatorMenuItem(), exitMenuItem);

		    editMenu.getItems().addAll(copieMenuItem, couperMenuItem, collerMenuItem,
                    selectionnerMenuItem, taileMenuItem,couleurMenuItem);

		    //ajout des elements sur linterface
		    //le menu
		    menuBar.getMenus().addAll(fileMenu);
		    menuBar.getMenus().addAll(editMenu);
		    //ajout html editor
		    HTMLEditor htmlEditor = new HTMLEditor();
		    root.setCenter(htmlEditor);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
