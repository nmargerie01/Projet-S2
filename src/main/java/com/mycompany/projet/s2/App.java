package com.mycompany.projet.s2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    private int p1, p2 ,p3;
    BooleanData doubleclic = new BooleanData();
    BooleanData deuxclic = new BooleanData();
    BooleanData troisclic = new BooleanData();
    BooleanData quatreclic = new BooleanData();
    VBox vboxrevet = new VBox();
    Label titre = new Label();
    VBox vboxrevet2 = new VBox();
    Label titre2 = new Label();
    Label lab3 = new Label();       
    TextField text3 = new TextField();
    HBox hbox = new HBox();
    ChoiceBox<String> creation = new ChoiceBox<>();



   
    @Override
    public void start(Stage stage) throws Exception {
        
        Recuperationdesrevetement();
        
        Barredemenu();
             
        
        for (int i=0;i<Principale.listeRevetement.size();i++){ 
            Label label = new Label (Principale.listeRevetement.get(i).afficherlegende());
            vboxrevet.getChildren().add(label);}        
        vboxrevet2.getChildren().clear();
        titre2.setText("");
        titre.setText("Tout les revetement"); 
        titre.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        titre2.setStyle("-fx-font-weight: bold; -fx-underline: true;");

        doubleclic.bool = true;
        deuxclic.bool = false;
        troisclic.bool = false;
        quatreclic.bool = false;
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
                c.afficher();}
            
            // Creation d'un mur
            // Création d'un mur
if (creation.getValue().equals("Mur")) {
    if (!doubleclic.bool) {
        // Premier clic
        x1 = Math.floor(event.getX() / cellSize) * cellSize;
        y1 = Math.floor(event.getY() / cellSize) * cellSize;
        doubleclic.bool = true;
    } else {
        // Deuxième clic
        x2 = Math.floor(event.getX() / cellSize) * cellSize;
        y2 = Math.floor(event.getY() / cellSize) * cellSize;
        // Créer le mur avec les deux points
        Line line = new Line(x1, y1, x2, y2);
        root.getChildren().add(line);
        Coin debut = Principale.recherchecoinparcoordonnee(x1, y1);
        Coin fin = Principale.recherchecoinparcoordonnee(x2, y2);
        fenetreparametre("Mur", "Nb de fenetres", "Nb de portes", "n° du revetement");
        Revetement revetement = Principale.rechercherevetement(p3);
        Mur mur = new Mur(Principale.listeMur.size() + 1, debut, fin, p1, p2, revetement);
        Principale.listeMur.add(mur);
        mur.afficher();
        // Réinitialiser pour le prochain mur
        doubleclic.bool = false;
    }
}

        
            // Creation d'une piece
            if (creation.getValue().equals("Piece") && (!deuxclic.bool)){
                x2 = Math.floor(event.getX() / cellSize) * cellSize;
                y2 = Math.floor(event.getY() / cellSize) * cellSize;
                deuxclic.bool = false;
                troisclic.bool = true;}
            
            if (creation.getValue().equals("Piece") && (!troisclic.bool)){
                x3 = Math.floor(event.getX() / cellSize) * cellSize;
                y3 = Math.floor(event.getY() / cellSize) * cellSize;
                troisclic.bool = false;
                quatreclic.bool = true;}
            
            if (creation.getValue().equals("Piece") && (!quatreclic.bool)){
                x4 = Math.floor(event.getX() / cellSize) * cellSize;
                y4 = Math.floor(event.getY() / cellSize) * cellSize;
                quatreclic.bool = false;
                Polygon rectangle = new Polygon(x1, y1, x2, y2, x3, y3, x4, y4);
                rectangle.setFill(Color.BLUE);
                root.getChildren().add(rectangle);
                ArrayList<Mur> listemurs = new ArrayList<>(); 
                Coin coin1 = Principale.recherchecoinparcoordonnee(x1, y1);
                Coin coin2 = Principale.recherchecoinparcoordonnee(x2, y2);
                Coin coin3 = Principale.recherchecoinparcoordonnee(x3, y3);
                Coin coin4 = Principale.recherchecoinparcoordonnee(x4, y4);
                Mur mur1 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                Mur mur2 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                Mur mur3 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                Mur mur4 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                listemurs.add(mur1);
                listemurs.add(mur2);
                listemurs.add(mur3);
                listemurs.add(mur4);
                fenetreparametre ("Pièce", "n° du revet. du sol","n° du revet. du plafond"," ");
                Revetement revsol = Principale.rechercherevetement(p1);
                Revetement revplafond = Principale.rechercherevetement(p2);                
                Sol sol = new Sol(Principale.listeSol.size()+1,coin1,coin2,coin3,coin4,revsol);
                Principale.listeSol.add(sol);
                sol.afficher();
                Plafond plafond = new Plafond(Principale.listePlafond.size()+1,coin1,coin2,coin3,coin4,revplafond);
                Principale.listePlafond.add(plafond);
                plafond.afficher();
                Piece p = new Piece(Principale.listePiece.size()+1,sol,plafond,listemurs);
                Principale.listePiece.add(p) ;
                p.afficher();}
            
            if (creation.getValue().equals("Piece")){
                x1 = Math.floor(event.getX() / cellSize) * cellSize;
                y1 = Math.floor(event.getY() / cellSize) * cellSize;
                deuxclic.bool = true;}

            // Légende   
                
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
   
    private void Barredemenu(){
    MenuItem item1 = new MenuItem("Ouvrir");
        MenuItem item2 = new MenuItem("Enregistrer");
        MenuItem item3 = new MenuItem("Fermer");
        Menu file = new Menu("Fichier");
        file.getItems().addAll(item1, item2, item3);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(file);
        menuBar.setUseSystemMenuBar(true);
        
        creation.getItems().addAll("Coin", "Mur", "Piece");
        creation.setValue("Coin");

        hbox.getChildren().addAll(menuBar, creation);}
    
    private void Recuperationdesrevetement(){
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
                r.afficher();
                Principale.listeRevetement.add(r);}} 
    
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n’existe pas! " + e);} 
    
        catch (IOException err){
            System.out.println("Erreur de lecture du fichier: " + err);}}
        
    private void fenetreparametre (String title, String parametre1,String parametre2,String parametre3) {
        Stage fenetreparametre = new Stage();
        fenetreparametre.setTitle(title);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label lab1 = new Label(parametre1);       
        TextField text1 = new TextField();      
        text1.setPromptText("Nombre");
                
        Label lab2 = new Label(parametre2);       
        TextField text2 = new TextField();      
        text2.setPromptText("Nombre");
        
        if (parametre3 != " "){
        lab3.setText(parametre3);       
        text3.setPromptText("Nombre");}
        
        Button valider = new Button("Valider");
        valider.setOnAction(event2 -> {
            p1 = Integer.valueOf(text1.getText());
            p2 = Integer.valueOf(text2.getText());
            p3 = Integer.valueOf(text3.getText());
            fenetreparametre.close();
            doubleclic.bool = false;});
        grid.add(lab1, 0, 0);
        grid.add(text1, 1, 0);
        grid.add(lab2, 0, 1);
        grid.add(text2, 1, 1);
        grid.add(lab3, 0, 2);
        grid.add(text3, 1, 2);
        grid.add(valider, 0, 3, 2, 1);
        Scene scene = new Scene(grid);
        fenetreparametre.setScene(scene);
        fenetreparametre.setTitle("Parametre");
        fenetreparametre.showAndWait();}
    
    public static void main (String[] args) {
        launch(args);
    }
}

