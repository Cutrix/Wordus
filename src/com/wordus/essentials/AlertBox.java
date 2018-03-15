package com.wordus.essentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    
    public static void box(String title,String message){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(200);
        
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
        textarea.setText("CREATE TABLE [nom_table] (\n"
                + "champ 1 typeDEDonnee,\n"
                + "champ 2 typeDeDonnee2\n)");
        
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
        
        
        Label label=new Label("Votre Requête SQL");
        
        TextArea textarea=new TextArea();
        textarea.setText("SELECT * FROM [nom_table] WHERE [condition]");
        
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
        
        Label label2=new Label("Votre Resultat ");
        
        TableView table=new TableView();
        
        VBox layout=new VBox();
        layout.getChildren().addAll(label,textarea,btn,label2);
        
        Scene scene =new Scene(layout);
        window.setScene(scene);
        window.show();
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
            Class.forName("org.sqlite.Driver");
            String url = "jdbc:sqlite://localhost:5432/Miage";
            String user = "miage";
            String passwd = "";
            conn = DriverManager.getConnection(url, user, passwd);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    
}
