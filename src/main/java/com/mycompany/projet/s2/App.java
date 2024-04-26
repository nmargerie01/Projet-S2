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
    private double x, y;
    private double a,b,c,d;
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
    BorderPane layout = new BorderPane();
    Pane root = new Pane(); 
    VBox legende = new VBox();
    Label echelle = new Label();
    Label indication = new Label();
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Recuperationdesrevetement();
        Barredemenu();
        Quadrillage();
        Legende();
        
        indication.setPadding(new Insets(0,0,0,20));
        echelle.setPadding(new Insets(0,20,0,20));
        echelle.setText("1carr. = 1m");
        echelle.setStyle("-fx-font-weight: bold");
        layout.setTop(hbox);
        layout.setRight(legende);
        layout.setCenter(root);
        
        doubleclic.bool = false;
        deuxclic.bool = false;
        troisclic.bool = false;
        quatreclic.bool = false;
        
       
        
        root.setOnMouseClicked(event -> {
            

            // Création d'un coin
            if (creation.getValue().equals("Coin")) {
                Legende();
                x = Math.floor(event.getX() / cellSize) * cellSize;
                y = Math.floor(event.getY() / cellSize) * cellSize;
                indication.setText("Cliquer pour mettre un coin");
                Coin();}
            
            // Creation d'un mur
            if (creation.getValue().equals("Mur")) {
                      
                if (doubleclic.bool == true){
                    c = Math.floor(event.getX() / cellSize) * cellSize;
                    d = Math.floor(event.getY() / cellSize) * cellSize;
                    fenetreparametre("Mur", "Nb de fenetres", "Nb de portes", "n° du revetement");
                    Mur();
                    indication.setText("Selectionner le 1er coin du mur");
                    doubleclic.bool = false;}       
                else {
                    a = Math.floor(event.getX() / cellSize) * cellSize;
                    b = Math.floor(event.getY() / cellSize) * cellSize;
                    indication.setText("Selectionner le 2eme coin du mur");
                    doubleclic.bool = true;}}

        
            // Creation d'une piece
            if (creation.getValue().equals("Piece")){
                Legendepiece();
                if (quatreclic.bool == true){
                    x4 = Math.floor(event.getX() / cellSize) * cellSize;
                    y4 = Math.floor(event.getY() / cellSize) * cellSize;
                    quatreclic.bool = false;
                    fenetreparametre ("Pièce", "n° du revet. du sol","n° du revet. du plafond","");
                    indication.setText("Selectionner le 1eme coin de la piece");
                    Piece();}
            
                else if (troisclic.bool == true){
                    x3 = Math.floor(event.getX() / cellSize) * cellSize;
                    y3 = Math.floor(event.getY() / cellSize) * cellSize;
                    troisclic.bool = false;
                    quatreclic.bool = true;
                    indication.setText("Selectionner le 4eme coin de la piece");}
            
                else if (deuxclic.bool == true){
                    x2 = Math.floor(event.getX() / cellSize) * cellSize;
                    y2 = Math.floor(event.getY() / cellSize) * cellSize;
                    indication.setText("Selectionner le 3eme coin de la piece");
                    deuxclic.bool = false;
                    troisclic.bool = true;}
            
                else{
                    x1 = Math.floor(event.getX() / cellSize) * cellSize;
                    y1 = Math.floor(event.getY() / cellSize) * cellSize;
                    indication.setText("Selectionner le 2eme coin de la piece");
                    deuxclic.bool = true;}}

               
                
            
            
            if (creation.getValue().equals("Mur")){
                vboxrevet.getChildren().clear();
                titre.setText("Revetement de mur"); 
                vboxrevet2.getChildren().clear();
                titre2.setText("");
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourMur == true) {
                        vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}}
            
            
        
        Scene scene = new Scene(layout, (cols * cellSize)+220, rows * cellSize);
        stage.setScene(scene);
        stage.setTitle("Devis");
        stage.show();
    });}
   
    private void Legende(){
        for (int i=0;i<Principale.listeRevetement.size();i++){ 
            Label label = new Label (Principale.listeRevetement.get(i).afficherlegende());
            vboxrevet.getChildren().add(label);}        
        vboxrevet2.getChildren().clear();
        titre2.setText("");
        titre.setText("Tout les revetement"); 
        titre.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        titre2.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        legende.getChildren().addAll(titre,vboxrevet,titre2,vboxrevet2);
        legende.setPadding(new Insets (20));
    }
    private void Legendemur(){
        vboxrevet.getChildren().clear();
        titre.setText("Revetement de mur"); 
        vboxrevet2.getChildren().clear();
        titre2.setText("");
            for (int i=0;i<Principale.listeRevetement.size();i++){                 
                if (Principale.listeRevetement.get(i).pourMur == true) {
                    vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}}    
    private void Legendepiece(){
        vboxrevet.getChildren().clear();
        titre.setText("Revetement de sol"); 
        for (int i=0;i<Principale.listeRevetement.size();i++){                 
            if (Principale.listeRevetement.get(i).pourSol == true) {
                vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}                
        vboxrevet2.getChildren().clear();
        titre2.setText("Revetement de plafond"); 
        for (int i=0;i<Principale.listeRevetement.size();i++){                 
            if (Principale.listeRevetement.get(i).pourPlafond == true) {
                vboxrevet2.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));
    }}}
    private void Piece (){
                Polygon rectangle = new Polygon(x1,y1,x2,y2,x3,y3,x4,y4);
                rectangle.setFill(Color.BEIGE);
                root.getChildren().add(rectangle);
                }
    private void Mur(){
        Line line = new Line(a, b, c, d);
        root.getChildren().add(line);
        Coin debut = Principale.recherchecoinparcoordonnee(a, b);
        Coin fin = Principale.recherchecoinparcoordonnee(c, d);
        Revetement revetement = Principale.rechercherevetement(p3);
        Mur mur = new Mur(Principale.listeMur.size() + 1, debut, fin, p1, p2, revetement);
        Principale.listeMur.add(mur);
        mur.afficher();}
    private void Coin(){
        Circle circle = new Circle(x, y, pointSize, Color.BLACK);
        root.getChildren().add(circle);
        Coin c = new Coin(Principale.listeCoin.size() + 1, x, y);
        Principale.listeCoin.add(c);
        c.afficher();
    }
    private void Quadrillage(){
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                double varx = col * cellSize;
                double vary = row * cellSize;
                root.getChildren().add(new Circle(varx, vary, 0.5, Color.BLACK));}}
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
        indication.setText("Indication");
        hbox.getChildren().addAll(menuBar, creation,echelle,indication);}
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
        
        if ((parametre3) != ("")){
        lab3.setText(parametre3);       
        text3.setPromptText("Nombre");
        grid.add(lab3, 0, 2);
        grid.add(text3, 1, 2);}
        
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
        grid.add(valider, 0, 3, 2, 1);
        Scene scene = new Scene(grid);
        fenetreparametre.setScene(scene);
        fenetreparametre.setTitle("Parametre");
        fenetreparametre.showAndWait();}
    public static void main (String[] args) {
        launch(args);
    }
}

