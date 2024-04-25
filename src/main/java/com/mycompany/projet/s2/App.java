package com.mycompany.projet.s2;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class App extends Application {

    private final int rows = 60; 
    private final int cols = 100; 
    private final int cellSize = 10; 
    private final int pointSize = 2; 
    private double x,y,w,z;
    private int fenetre, porte ,revet;


    @Override
    public void start(Stage stage) throws Exception {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("Fichier");

        menuBar.getMenus().addAll(file);
        menuBar.setUseSystemMenuBar(true);

        MenuItem item1 = new MenuItem("Open");
        MenuItem item2 = new MenuItem("Save");
        MenuItem item3 = new MenuItem("Exit");

        file.getItems().addAll(item1, item2, item3);

        ChoiceBox<String> creation = new ChoiceBox<>();
        creation.getItems().addAll("Coin", "Mur", "Piece");
        creation.setValue("Coin");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(menuBar, creation);

        BorderPane layout = new BorderPane();
        layout.setTop(hbox);

        Pane root = new Pane();

        // Quadrillage
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                double x = col * cellSize;
                double y = row * cellSize;
                root.getChildren().add(new Circle(x, y, 0.5, Color.BLACK));}}
        
        // Création d'un coin
        if(creation.getValue() == "Coin"){
            root.setOnMouseClicked(event -> {
                double x = Math.floor(event.getX() / cellSize) * cellSize;
                double y = Math.floor(event.getY() / cellSize) * cellSize;
                Circle circle = new Circle(x, y, pointSize, Color.BLACK);
                root.getChildren().add(circle);
                Coin c = new Coin(Principale.listeCoin.size()+1,x,y);
                Principale.listeCoin.add(c);});}
        
        // Création d'un mur
        if(creation.getValue() == "Mur"){
            root.setOnMousePressed(event -> {
                x = Math.floor(event.getX() / cellSize) * cellSize;
                y = Math.floor(event.getY() / cellSize) * cellSize;});
            root.setOnMouseReleased(event -> {
                w = Math.floor(event.getX() / cellSize) * cellSize;
                z = Math.floor(event.getY() / cellSize) * cellSize;
                Line line = new Line (x,y,w,z);
                Coin debut = Principale.recherchecoinparcoordonnee(x,y);
                Coin fin = Principale.recherchecoinparcoordonnee(w,z);
                Stage parametreStage = new Stage();
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(20));
                grid.setHgap(10);
                grid.setVgap(10);
                Label labfenetre = new Label("Nombre de fenêtres:");
                TextField textfenetre = new TextField();
                textfenetre.setPromptText("Nombre");
                Label labporte = new Label("Nombre de portes:");
                TextField textporte = new TextField();
                textporte.setPromptText("Nombre");
                Label labrevet = new Label("Numero du revetement");
                TextField textrevet = new TextField();
                textrevet.setPromptText("Nombre");    
                Button valider = new Button("Valider");
                valider.setOnAction(event2 -> {
                    fenetre = Integer.valueOf(textfenetre.getText());
                    porte = Integer.valueOf(textporte.getText());
                    revet = Integer.valueOf(textrevet.getText());
                    parametreStage.close();});
                grid.add(labfenetre, 0, 0);
                grid.add(textfenetre, 1, 0);
                grid.add(labporte, 0, 1);
                grid.add(textporte, 1, 1);
                grid.add(labrevet, 0, 2);
                grid.add(textrevet, 1, 2);
                grid.add(valider, 0, 3, 2, 1);
                Scene scene = new Scene(grid);
                parametreStage.setScene(scene);
                parametreStage.setTitle("Parametre");
                parametreStage.show(); 
                Revetement revetement = Principale.rechercherevetement(revet);
                Mur mur = new Mur(Principale.listeMur.size()+1,debut,fin,fenetre,porte,revetement);
            });}

        
        layout.setCenter(root);

        Scene scene = new Scene(layout, cols * cellSize, rows * cellSize);
        stage.setScene(scene);
        stage.setTitle("Menu et Quadrillage avec Points");
        stage.show();
    }
   
    public static void main(String[] args) {
        launch(args);
    }
}

