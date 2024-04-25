package com.mycompany.projet.s2;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class App extends Application {

    private final int rows = 60; // Nombre de lignes
    private final int cols = 100; // Nombre de colonnes
    private final int cellSize = 10; // Taille d'une cellule
    private final int pointSize = 2; // Taille des points
    private double x,y,w,z;


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

        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("JavaFX MenuBar");
                alert.setHeaderText("Menu Item");
                alert.setContentText("Clicked");
                alert.show();
            }
        });

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
                z = Math.floor(event.getY() / cellSize) * cellSize;});
            Line line = new Line (x,y,w,z) ;
            Coin debut = Principale.recherchecoinparcoordonnee(x,y);
            Mur mur = new Mur(Principale.listeMur.size()+1,debut,y);
            }

        
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

