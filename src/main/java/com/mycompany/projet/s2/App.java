package com.mycompany.projet.s2;

import java.io.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fichier);
        menuBar.setUseSystemMenuBar(true);
        
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        
        Menu fichier = new Menu("Fichier");
        MenuItem item1 = new MenuItem("Ouvrir");
        MenuItem item2 = new MenuItem("Enregistrer");
        MenuItem item3 = new MenuItem("Fermer");
        Fichier.getItems().addAll(item1,item2,item3);        
        item1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("JavaFX MenuBar");
                alert.setHeaderText("Menu Item");
                alert.setContentText("Clicked");
                alert.show();
            }
        });

        
        ChoiceBox Creation = new ChoiceBox<String>
                
        
        
        


        
        Scene scene = new Scene (layout, 500,500);
        stage.setScene(scene);
        stage.setTitle("menubar");
        stage.show();
    }
    
    private void alert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("JavaFX MenuBar");
        alert.setHeaderText("Menu Item");
        alert.setContentText("Clicked");
        alert.show();
        }
    
    public static void main(String[] args) {
        launch();
    }

}