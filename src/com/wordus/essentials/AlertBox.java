package com.wordus.essentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    private String[] colums;
    
    public static void box(String title,String message){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(200);
        window.getIcons().add(new Image("font/icon/text-editor.png"));
        
        Label label=new Label(message);
        
        VBox layout=new VBox();
        layout.getChildren().add(label);
        Scene scene =new Scene(layout);
        window.setScene(scene);
        
        window.show();
    }
    
    
    public static void displayCreateTable(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Creation d'une table");
        window.setMinWidth(200);
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        /*textarea.setText("CREATE TABLE [nom_table] (\n"
                +        "champ 1 typeDEDonnee,\n"
                +        "champ 2 typeDeDonnee2\n)");*/

        textarea.setText("CREATE TABLE RITA ( \n" +
                "ID PRIMARY KEY NOT NULL,\n" +
                "NOM TEXT NOT NULL,\n" +
                "PRENOM TEXT NOT NULL\n" +
                ")");

        //System.out.println(textarea.getText());
        //System.exit(0);
        
        Button btn=new Button("Executer Votre Requête");
        btn.setOnAction(e-> {
            try{
                Connection conn=connection();
                Statement state = conn.createStatement();
                String query = textarea.getText();
                ResultSet result = state.executeQuery(query);

                System.out.println("Table creer");

                if(result.next()){
                    AlertBox.box("Succes","VOTRE REQUÊTE S'EST EXECUTEE AVEC SUCCES");
                }else{
                    AlertBox.box("Echec","VOTRE REQUÊTE A ECHOUEE");
                }
                
            }catch(Exception ex){ex.printStackTrace();}
        });
        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn);
        
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        
        window.show();
    }
    
    public static void displayUpdateTable(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modification d'une table");
        window.setMinWidth(200);
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        textarea.setText("UPDATE TABLE  [nom_table] ");
        
        Button btn=new Button("Executer Votre Requête");
        btn.setOnAction(e-> {
            try{
                Connection conn=connection();
                Statement state = conn.createStatement();
                String query = textarea.getText();
                ResultSet result = state.executeQuery(query);

                if(result.next()){
                    AlertBox.box("Succes","VOTRE REQUÊTE S'EST EXECUTEE AVEC SUCCES");
                }else{
                    AlertBox.box("Echec","VOTRE REQUÊTE A ECHOUEE");
                }
                
            }catch(Exception ex){ex.printStackTrace();}
        });
        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn);
        
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        
        window.show();
    }
    
    public static void displayDropTable(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Suppression d'une table");
        window.setMinWidth(200);
        
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        textarea.setText("DROP TABLE [nom_table]");
        
        Button btn=new Button("Executer Votre Requête");
        btn.setOnAction(e-> {
            try{
                Connection conn=connection();
                Statement state = conn.createStatement();
                String query = textarea.getText();
                ResultSet result = state.executeQuery(query);

                if(result.next()){
                    AlertBox.box("Succes","VOTRE REQUÊTE S'EST EXECUTEE AVEC SUCCES");
                }else{
                    AlertBox.box("Echec","VOTRE REQUÊTE A ECHOUEE");
                }
                
            }catch(Exception ex){ex.printStackTrace();}
            
        });
        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn);
        
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        
        window.show();
    }
    
    public static void displaySelectData(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sélection De Donnée");
        window.setMinWidth(200);
        ListView<String> listView = new ListView<>();
        
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        textarea.setText("SELECT * FROM [nom_table] WHERE [condition]");
        Label label2 = new Label("Votre resultat");
        Button btn=new Button("Executer Votre Requête");
        btn.setOnAction(e-> { 
            try{
                Connection conn=connection();
                Statement state = conn.createStatement();
                String query = textarea.getText();
                ResultSet result = state.executeQuery(query);

                ArrayList<String> alMyColumns = new ArrayList<>();
                //System.out.println(AlertBox.getColumnCount(result));
                //System.out.println(AlertBox.getColumnNameArray(result));
                for (int i = 0 ; i < AlertBox.getColumnCount(result) ; i++) {
                    //System.out.println(AlertBox.getColumnNameArray(result)[i].join(", "));
                    alMyColumns.add(AlertBox.getColumnNameArray(result)[i]);
                }


                //System.out.println(String.join(", ", alMyColumns));

                String[] myColumns = new String[alMyColumns.size()];
                myColumns = alMyColumns.toArray(myColumns);

                ArrayList<String> valuesColumns = new ArrayList<>();

                while (result.next()) {

                    for (int c = 0 ; c < alMyColumns.size() ; c++) {
                        //System.out.println(result.getString(myColumns[c]));
                        valuesColumns.add(result.getString(myColumns[c]));
                    }
                }

                ObservableList<String> nameSQL = FXCollections.observableArrayList(valuesColumns);
                listView.setItems(nameSQL);


                if(result.next()){
                    AlertBox.box("Succes","VOTRE REQUÊTE S'EST EXECUTEE AVEC SUCCES");
                }else{
                    AlertBox.box("Echec","VOTRE REQUÊTE A ECHOUEE");
                }
                
            }catch(Exception ex){ex.printStackTrace();}
        });
        

        
        TableView table=new TableView();

        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn,label2,listView);
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private static String[] getColumnNameArray(ResultSet rs) {
        String myArr[] = null;
        try {
            ResultSetMetaData rm = rs.getMetaData();
            myArr = new String[rm.getColumnCount()];
            for (int ctr = 1; ctr <= myArr.length; ctr++) {
                String s = rm.getColumnName(ctr);
                myArr[ctr - 1] = s;
            }
        } catch (Exception e) {
            System.out.println(e);
            return myArr;
        }
        return myArr;
    }

    private static int getColumnCount(ResultSet rs) {
        int iOutput = 0;
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            iOutput = rsMetaData.getColumnCount();

        } catch (Exception e) {
            System.out.println(e);
            return iOutput = -1;
        }
        return iOutput;
    }

    public static void displayInsertData(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Insertion De Donnée");
        window.setMinWidth(200);
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        textarea.setText("INSERT INTO [nom_table] WHERE [condition]");
        
        Button btn=new Button("Executer Votre Requête");
        btn.setOnAction(e-> {
            try{
                Connection conn=connection();
                Statement state = conn.createStatement();
                String query = textarea.getText();
                ResultSet result = state.executeQuery(query);

                ResultSetMetaData resultMeta = result.getMetaData();
                
            }catch(Exception ex){ex.printStackTrace();}
        });
        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn);
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    public static void displayDeleteData(){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Suppression De Donnée");
        window.setMinWidth(200);
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        textarea.setText("DELETE * FROM [nom_table] WHERE [condition]");
        
        Button btn=new Button("Executer Votre Requête");
        btn.setOnAction(e-> {
            try{
                Connection conn=connection();
                Statement state = conn.createStatement();
                String query = textarea.getText();
                ResultSet result = state.executeQuery(query);

                ResultSetMetaData resultMeta = result.getMetaData();
                
            }catch(Exception ex){ex.printStackTrace();}
  
        });
        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn);
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    
    private static Connection connection(){
        Connection conn=null;
        try{
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:test.db";
            /*String user = "miage";
            String passwd = "";*/
            conn = DriverManager.getConnection(url);
            System.out.println("Connexion reussie");
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    
}
