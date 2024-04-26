package com.mycompany.projet.s2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
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
    private double x1,x2,x3,x4,y1,y2,y3,y4;
    private int fenetre, porte ,revet;
    BooleanData doubleclic = new BooleanData();
    BooleanData tripleclic = new BooleanData();
    BooleanData quatreclic = new BooleanData();
    BooleanData dernierclic = new BooleanData();
    VBox vboxrevet = new VBox();
    Label titre = new Label();
    VBox vboxrevet2 = new VBox();
    Label titre2 = new Label();

   
    @Override
    public void start(Stage stage) throws Exception {
        
        // Récupération des revetement
        try { 
            Principale.listeRevetement.clear();
            File csvFile = new File("C:\\Users\\natha\\Documents\\NetBeansProjects\\Projet-S2\\src\\main\\java\\com\\mycompany\\projet\\s2\\CatalogueRevetements.txt");
            FileReader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] parties = line.split(";");
                int id = Integer.parseInt(parties[0]);
                String nom = parties[1];
                boolean mur = false;
                if (Integer.valueOf(parties[2]) == 1){
                    mur = true;}
                boolean sol = false;
                if (Integer.valueOf(parties[3]) == 1){
                    sol = true;}
                boolean plafond = false;
                if (Integer.valueOf(parties[4]) == 1){
                    plafond = true;}
                double prix = Double.parseDouble(parties[5]);

                Revetement r = new Revetement(id, nom, mur, sol, plafond, prix);
                Principale.listeRevetement.add(r);}} 
    
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n’existe pas! " + e);} 
    
        catch (IOException err){
            System.out.println("Erreur de lecture du fichier: " + err);}
        
        // Barre de menu
        
        MenuItem item1 = new MenuItem("Ouvrir");
        MenuItem item2 = new MenuItem("Enregistrer");
        MenuItem item3 = new MenuItem("Fermer");
        Menu file = new Menu("Fichier");
        file.getItems().addAll(item1, item2, item3);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file);
        menuBar.setUseSystemMenuBar(true);
        
        ChoiceBox<String> creation = new ChoiceBox<>();
        creation.getItems().addAll("Coin", "Mur", "Piece");
        creation.setValue("Coin");

        HBox hbox = new HBox();
        hbox.getChildren().addAll(menuBar, creation);
        
        for (int i=0;i<Principale.listeRevetement.size();i++){ 
            Label label = new Label (Principale.listeRevetement.get(i).afficherlegende());
            vboxrevet.getChildren().add(label);}        
        vboxrevet2.getChildren().clear();
        titre2.setText("");
        titre.setText("Tout les revetement"); 
        titre.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        
        VBox legende = new VBox(titre,vboxrevet,titre2,vboxrevet2);
        legende.setPadding(new Insets (20));
        
        BorderPane layout = new BorderPane();
        Pane root = new Pane(); 
        layout.setTop(hbox);
        layout.setRight(legende);
        layout.setCenter(root);

        // Quadrillage
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                double varx = col * cellSize;
                double vary = row * cellSize;
                root.getChildren().add(new Circle(varx, vary, 0.5, Color.BLACK));}}
        
        root.setOnMouseClicked(event -> {
                    
            // Création d'un coin
            if (creation.getValue().equals("Coin")) {
                double x = Math.floor(event.getX() / cellSize) * cellSize;
                double y = Math.floor(event.getY() / cellSize) * cellSize;
                Circle circle = new Circle(x, y, pointSize, Color.BLACK);
                root.getChildren().add(circle);
                Coin c = new Coin(Principale.listeCoin.size() + 1, x, y);
                Principale.listeCoin.add(c);
                doubleclic.bool = false;}
            
            // Creation d'un mur
            if (creation.getValue().equals("Mur") && (doubleclic.bool == true)){
                x2 = Math.floor(event.getX() / cellSize) * cellSize;
                y2 = Math.floor(event.getY() / cellSize) * cellSize;
                Line line = new Line (x1,y1,x2,y2);
                root.getChildren().add(line);
                Coin debut = Principale.recherchecoinparcoordonnee(x1,y1);
                Coin fin = Principale.recherchecoinparcoordonnee(x2,y2);
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
                    parametreStage.close();
                    doubleclic.bool = false;
                    Revetement revetement = Principale.rechercherevetement(revet);
                    Mur mur = new Mur(Principale.listeMur.size()+1,debut,fin,fenetre,porte,revetement);
                    mur.afficher();});
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
                parametreStage.show();}
                
            if (creation.getValue().equals("Mur")){
                x1 = Math.floor(event.getX() / cellSize) * cellSize;
                y1 = Math.floor(event.getY() / cellSize) * cellSize;
                doubleclic.bool = true;}
        
            // Creation d'une piece
            if (creation.getValue().equals("Piece")){
                x1 = Math.floor(event.getX() / cellSize) * cellSize;
                y1 = Math.floor(event.getY() / cellSize) * cellSize;
                Rectangle square = new Rectangle();
                root.getChildren().add(square);
                Coin debut = Principale.recherchecoinparcoordonnee(x1,y1);
                Coin fin = Principale.recherchecoinparcoordonnee(x2,y2);
                
                
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
                    parametreStage.close();
                    doubleclic.bool = false;
                    Revetement revetement = Principale.rechercherevetement(revet);
                    Mur mur = new Mur(Principale.listeMur.size()+1,debut,fin,fenetre,porte,revetement);
                    mur.afficher();});
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
                parametreStage.show();}
        
            if (creation.getValue().equals("Coin")){
                
                vboxrevet.getChildren().clear();
                titre.setText("Tout les revetement"); 
                vboxrevet2.getChildren().clear();
                titre2.setText("");
                for (int i=0;i<Principale.listeRevetement.size();i++){ 
                    Label label = new Label (Principale.listeRevetement.get(i).afficherlegende());
                    vboxrevet.getChildren().add(label);}}
            
            if (creation.getValue().equals("Mur")){

                vboxrevet.getChildren().clear();
                titre.setText("Revetement de mur"); 
                vboxrevet2.getChildren().clear();
                titre2.setText("");
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourMur == true) {
                        vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}}
            
            if (creation.getValue().equals("Piece")){
                
                vboxrevet.getChildren().clear();
                titre.setText("Revetement de sol"); 
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourSol == true) {
                        vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}
                
                vboxrevet2.getChildren().clear();
                titre2.setText("Revetement de plafond"); 
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourPlafond == true) {
                        vboxrevet2.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}}});
    
       
        
        Scene scene = new Scene(layout, (cols * cellSize)+220, rows * cellSize);
        stage.setScene(scene);
        stage.setTitle("Devis");
        stage.show();
    }
    
    private void fenetreparametrefpr (String title, String message) {
        Stage fenetreparametrefpr = new Stage();
        fenetreparametrefpr.initModality(Modality.APPLICATION_MODAL); // Bloque les interactions avec les autres fenêtres jusqu'à ce que cette fenêtre soit fermée
        fenetreparametrefpr.setTitle(title);
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> fenetreparametrefpr.close());

        StackPane layout = new StackPane(closeButton);
        Scene scene = new Scene(layout, 250, 150);
        fenetreparametrefpr.setScene(scene);
        fenetreparametrefpr.show();
    
    public static void main (String[] args) {
        launch(args);
    }
}

