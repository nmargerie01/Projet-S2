package com.mycompany.projet.s2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    private final int rows = 40; // Nombre de lignes
    private final int cols = 40; // Nombre de colonnes
    private final int cellSize = 10; // Taille d'une cellule
    private final int pointSize = 2; // Taille des points

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
        creation.getItems().addAll("Coin", "Mur");
        creation.setValue("Coin");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(menuBar, creation);

        BorderPane layout = new BorderPane();
        layout.setTop(hbox);

        Pane root = new Pane();

        // Dessiner le quadrillage
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                double x = col * cellSize;
                double y = row * cellSize;
                root.getChildren().add(new Circle(x, y, 1, Color.BLACK)); // Intersection du quadrillage
            }
        }

        // Gestion du clic pour placer un point
        root.setOnMouseClicked(event -> {
            double x = Math.floor(event.getX() / cellSize) * cellSize;
            double y = Math.floor(event.getY() / cellSize) * cellSize;
            Circle circle = new Circle(x, y, pointSize, Color.BLACK); // Dessiner le point en noir et un peu plus petit
            root.getChildren().add(circle);
        });

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

