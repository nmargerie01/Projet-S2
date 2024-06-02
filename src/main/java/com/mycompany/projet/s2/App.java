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

    // GESTION DE FICHIER
    static String cheminacces;                  //Chemin d'accès pour trouver le fichier à ouvrir
    static String nomDeSauvegarde;              //Nom du fichier de l'enregistrement
    
    // QUADRILLAGE
    private final int ligne = 120; 
    private final int colonne = 200; 
    private final int taillecase = 5;
    
    // COIN
    static double x,y;
    
    // PIECE
    static double x1,x2,x3,x4,y1,y2,y3,y4;
    BooleanData doubleclic = new BooleanData();
    BooleanData deuxclic = new BooleanData();
    BooleanData troisclic = new BooleanData();
    BooleanData quatreclic = new BooleanData();
    
    // NIVEAU
    ArrayList<Appartement>listeAppart=new ArrayList<>();
    static int n = 1;                                           //nb de niveau - compteur
    
    // LEGENDE
    HBox hbox = new HBox();
    ChoiceBox<String> creation = new ChoiceBox<>();
    ChoiceBox<String> level = new ChoiceBox<>();
    VBox legende = new VBox();
    Label echelle = new Label();
    static Label indication = new Label();
    Button niveaux = new Button("Niveau +");
    VBox vboxrevet = new VBox();
    VBox vboxrevet2 = new VBox();
    Label titre = new Label();
    Label titre2 = new Label();
    MenuBar menuBar = new MenuBar();
    Button creerappart = new Button("Creer un appart");
    MenuItem devis = new MenuItem("Devis");
    MenuItem ouvrir = new MenuItem("Ouvrir");
    MenuItem enregistrer = new MenuItem("Enregistrer");
    Menu file = new Menu("Fichier");
    
    // FENETRE DE PARAMETRE
    static double h;
    static String p1,p2,p3;
    Label labparametre3 = new Label();       
    TextField text3 = new TextField();
    
    // INTERFACE
    BorderPane layout = new BorderPane();
    static Pane root = new Pane(); 
       
    ArrayList<Polygon> listedesrecpiece = new ArrayList<>();
    ArrayList<Piece> listedespiecechoisie = new ArrayList<>();
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Recuperationdesrevetement();
        Barredemenu();
        Quadrillage();
        Legende();
        
               
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
                legendecoin();
                x = Math.floor(event.getX() / taillecase) * taillecase;
                y = Math.floor(event.getY() / taillecase) * taillecase;               
                Coin c = new Coin(Principale.listeCoin.size() + 1, x, y);
                indication.setText("Cliquer pour mettre un coin");
                Principale.listeCoin.add(c);
                c.afficher();}
            
            // Creation d'un mur
            if (creation.getValue().equals("Mur")) {
                legendemur();     
                if (doubleclic.bool == true){
                    x2 = Math.floor(event.getX() / taillecase) * taillecase;
                    y2 = Math.floor(event.getY() / taillecase) * taillecase;
                    fenetreparametre("Mur", "Nb de fenetres", "Nb de portes", "Revetement(s)");
                    Mur();
                    indication.setText("Selectionner le 1er coin du mur");                    
                    doubleclic.bool = false;}       
                else {
                    x1 = Math.floor(event.getX() / taillecase) * taillecase;
                    y1 = Math.floor(event.getY() / taillecase) * taillecase;
                    indication.setText("Selectionner le 2eme coin du mur");
                    doubleclic.bool = true;}}

        
            // Creation d'une piece
            if (creation.getValue().equals("Piece")){
                hbox.getChildren().remove(creerappart);
                vboxrevet.getChildren().clear();
                titre.setText("Revetement de sol"); 
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourSol == true) {
                        vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}                
                vboxrevet2.getChildren().clear();
                titre2.setText("Revetement de plafond"); 
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourPlafond == true) {
                        vboxrevet2.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}
                if (quatreclic.bool == true){
                    x4 = Math.floor(event.getX() / taillecase) * taillecase;
                    y4 = Math.floor(event.getY() / taillecase) * taillecase;
                    quatreclic.bool = false;
                    indication.setText("Selectionner le 1eme coin de la piece");
                    Piece();}
                else if (troisclic.bool == true){
                    x3 = Math.floor(event.getX() / taillecase) * taillecase;
                    y3 = Math.floor(event.getY() / taillecase) * taillecase;
                    troisclic.bool = false;
                    quatreclic.bool = true;
                    indication.setText("Selectionner le 4eme coin de la piece");}
                else if (deuxclic.bool == true){
                    x2 = Math.floor(event.getX() / taillecase) * taillecase;
                    y2 = Math.floor(event.getY() / taillecase) * taillecase;
                    deuxclic.bool = false;
                    troisclic.bool = true;
                    indication.setText("Selectionner le 3eme coin de la piece");}
                else{
                    x1 = Math.floor(event.getX() / taillecase) * taillecase;
                    y1 = Math.floor(event.getY() / taillecase) * taillecase;
                    deuxclic.bool = true;
                    indication.setText("Selectionner le 2eme coin de la piece");}}

            if (creation.getValue().equals("Appart")){
                double X = event.getX();
                double Y = event.getY();
                vboxrevet.getChildren().clear();
                titre.setText("Tout les revetement"); 
                vboxrevet2.getChildren().clear();
                titre2.setText("");
                for (int i=0;i<Principale.listeRevetement.size();i++){ 
                    Label label = new Label (Principale.listeRevetement.get(i).afficherlegende());
                    vboxrevet.getChildren().add(label);}
                indication.setText("Selection toutes les pièces, puis appuyer sur le bouton");
                hbox.getChildren().add(creerappart);              
                for (int i=0;h<listedesrecpiece.size();i++){
                    Polygon rectangle = listedesrecpiece.get(i);
                    x1 = rectangle.getPoints().get(0);
                    y1 = rectangle.getPoints().get(1);
                    x2 = rectangle.getPoints().get(2);
                    y2 = rectangle.getPoints().get(3);
                    x3 = rectangle.getPoints().get(4);
                    y3 = rectangle.getPoints().get(5);
                    x4 = rectangle.getPoints().get(6);
                    y4 = rectangle.getPoints().get(7);
                    double X1 = Math.min(Math.min(x1, x2), Math.min(x3, x4));
                    double Y1 = Math.min(Math.min(y1, y2), Math.min(y3, y4));
                    double X2 = Math.max(Math.max(x1, x2), Math.max(x3, x4));
                    double Y2 = Math.max(Math.max(y1, y2), Math.max(y3, y4));
                    Mur mur1 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                    Mur mur2 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                    Mur mur3 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                    Mur mur4 = Principale.recherchemurparcoordonnee(x1, y1, x2, x2);
                    if (X >= X1 && X <= X2 && Y >= Y1 && Y <= Y2){
                        for (Piece piece : Principale.listePiece){
                            if (piece.listeMurs.contains(mur1) && piece.listeMurs.contains(mur2) && piece.listeMurs.contains(mur3) && piece.listeMurs.contains(mur4)) {
                                listedespiecechoisie.add(piece);}}}}}
                        
            });
        
        creerappart.setOnAction(event4 -> {
            Appartement a = new Appartement(Principale.listeAppartement.size()+1,n,listedespiecechoisie);
            listedespiecechoisie.clear();
            Principale.listeAppartement.add(a);});
        
        niveaux.setOnAction(event3 -> {
            n++;
            level.getItems().add("Niveau "+n);
            level.setValue("Niveau "+n);
            root.getChildren().clear();
            Quadrillage();
            for (Appartement ap : Principale.listeAppartement){
                if (ap.idNiveauAppartement==(n-1)){
                    listeAppart.add(ap);}}
            Niveau niveau = new Niveau(Principale.listeNiveau.size()+1,h,listeAppart);
            listeAppart.clear();});
        
        devis.setOnAction(event4 -> {
            Batiment batiment = Principale.listeBatiment.get(0);
            if (Principale.listeBatiment.isEmpty()){
                batiment = new Batiment(1,Principale.listeNiveau);
                Principale.listeBatiment.add(batiment);}    
            fenetredevis();});
        
        ouvrir.setOnAction(event4 -> {
            fenetreouvrir();
            Batiment.lireBatiment();});
        
        enregistrer.setOnAction(event4 -> {
            Batiment batiment = Principale.listeBatiment.get(0);
            if (Principale.listeBatiment.isEmpty()){
                batiment = new Batiment(1,Principale.listeNiveau);
                Principale.listeBatiment.add(batiment);}           
            fenetreenregistrer();
            batiment.sauvegarder();
        });
        
        Scene scene = new Scene(layout, (colonne * taillecase)+240, ligne * taillecase);
        stage.setScene(scene);
        stage.setTitle("Devis");
        stage.show();
        fenetreniveau();
    }
   
    private void Legende(){
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
        legende.getChildren().addAll(titre,vboxrevet,titre2,vboxrevet2);
        legende.setPadding(new Insets (20));
    }
    private void legendecoin(){
        hbox.getChildren().remove(creerappart);
                vboxrevet.getChildren().clear();
                titre.setText("Tout les revetement"); 
                vboxrevet2.getChildren().clear();
                titre2.setText("");
                for (int i=0;i<Principale.listeRevetement.size();i++){ 
                    Label label = new Label (Principale.listeRevetement.get(i).afficherlegende());
                    vboxrevet.getChildren().add(label);}
    }
    private void legendemur(){
        hbox.getChildren().remove(creerappart);
                vboxrevet.getChildren().clear();
                titre.setText("Revetement de mur"); 
                vboxrevet2.getChildren().clear();
                titre2.setText("");
                for (int i=0;i<Principale.listeRevetement.size();i++){                 
                    if (Principale.listeRevetement.get(i).pourMur == true) {
                        vboxrevet.getChildren().add(new Label (Principale.listeRevetement.get(i).afficherlegende()));}}
    }
    private void Piece(){
        ArrayList listemurs = new ArrayList(); 
        Coin coin1 = Principale.recherchecoinparcoordonnee(x1, y1);
        Coin coin2 = Principale.recherchecoinparcoordonnee(x2, y2);
        Coin coin3 = Principale.recherchecoinparcoordonnee(x3, y3);
        Coin coin4 = Principale.recherchecoinparcoordonnee(x4, y4);
        Mur mur1 = Principale.recherchemurparcoordonnee(x1, y1, x2, y2);
        Mur mur2 = Principale.recherchemurparcoordonnee(x3, y3, x2, y2);
        Mur mur3 = Principale.recherchemurparcoordonnee(x3, y3, x4, y4);
        Mur mur4 = Principale.recherchemurparcoordonnee(x1, y1, x4, y4);
        listemurs.add(mur1.idMur);
        listemurs.add(mur2.idMur);
        listemurs.add(mur3.idMur);
        listemurs.add(mur4.idMur);
        fenetreparametre ("Pièce", "n° du revet. du sol","n° du revet. du plafond","");
        ArrayList revsol = new ArrayList();
        revsol.add(p1);
        ArrayList revplafond = new ArrayList();
        revplafond.add(p2);      
        ArrayList<Coin> listedecoins = new ArrayList<>();
        listedecoins.add(coin1);
        listedecoins.add(coin2);
        listedecoins.add(coin3);
        listedecoins.add(coin4);
        Sol sol = new Sol(Principale.listeSol.size()+1,listedecoins,revsol);
        Principale.listeSol.add(sol);
        sol.afficher();
        Plafond plafond = new Plafond(Principale.listePlafond.size()+1,listedecoins,revplafond);
        Principale.listePlafond.add(plafond);
        plafond.afficher();
        Piece p = new Piece(Principale.listePiece.size()+1,sol,plafond,listemurs);
        Principale.listePiece.add(p) ;
        Polygon rectangle = new Polygon(x1,y1,x2,y2,x3,y3,x4,y4);
        rectangle.setFill(Color.GREY);
        listedesrecpiece.add(rectangle);
        root.getChildren().add(rectangle);
        rectangle.toBack();
        p.afficher();
    }
    private void Mur(){
        Coin debut = Principale.recherchecoinparcoordonnee(x1, y1);
        Coin fin = Principale.recherchecoinparcoordonnee(x2, y2);
        ArrayList<Revetement> revetmur = new ArrayList<>();
        String[] parties = p3.split(",");
        int n = parties.length;
        for(int i=0;i<=n;i++){
        revetmur.add(Principale.rechercherevetement(Integer.parseInt(parties[i])));}
        Mur mur = new Mur(Principale.listeMur.size() + 1, debut, fin, Integer.parseInt(p1), Integer.parseInt(p2), revetmur);
        Principale.listeMur.add(mur);
        mur.afficher();}
    private void Quadrillage(){
        for (int row = 0; row <= ligne; row++) {
            for (int col = 0; col <= colonne; col++) {
                double varx = col * taillecase;
                double vary = row * taillecase;
                root.getChildren().add(new Circle(varx, vary, 0.5, Color.BLACK));}}
    }
    private void Barredemenu(){
        file.getItems().addAll(devis, ouvrir, enregistrer);
        menuBar.getMenus().addAll(file);
        menuBar.setUseSystemMenuBar(true);
        
        creation.getItems().addAll("Coin", "Mur", "Piece", "Appart");
        creation.setValue("Coin");
        creation.setPadding(new Insets(0,0,0,5));
        
        level.getItems().add("Niveau "+n);
        level.setValue("Niveau "+n);
        level.setPadding(new Insets(0,0,0,5));
        
        echelle.setText("1carr. = 10cm");
        echelle.setPadding(new Insets(5,0,0,20));
        echelle.setStyle("-fx-font-weight: bold");
        
        indication.setText("Indication");
        indication.setPadding(new Insets(5,20,0,20));
        
        hbox.getChildren().addAll(menuBar, creation,level,niveaux,echelle,indication);}
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
        
        Label labparametre1 = new Label(parametre1);       
        TextField text1 = new TextField();      
        text1.setPromptText("Nombre");
                
        Label labparametre2 = new Label(parametre2);       
        TextField text2 = new TextField();      
        text2.setPromptText("Nombre");
        
        if ((parametre3) != ("")){
        labparametre3.setText(parametre3);       
        grid.add(labparametre3, 0, 2);
        grid.add(text3, 1, 2);}
        
        Button valider = new Button("Valider");
        valider.setOnAction(event2 -> {
            p1 = text1.getText();
            p2 = text2.getText();
            p3 = text3.getText();
            fenetreparametre.close();
            doubleclic.bool = false;});
        grid.add(labparametre1, 0, 0);
        grid.add(text1, 1, 0);
        grid.add(labparametre2, 0, 1);
        grid.add(text2, 1, 1);
        grid.add(valider, 0, 3, 2, 1);
        Scene scene = new Scene(grid);
        fenetreparametre.setScene(scene);
        fenetreparametre.setTitle("Parametre");
        fenetreparametre.showAndWait();}
    private void fenetreniveau () {
        Stage fenetreniveau = new Stage();
        fenetreniveau.setTitle("Hauteur sous plafond du niveau "+n);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label lab = new Label("Hauteur sous plafond");       
        TextField text = new TextField();      
        text.setPromptText("Nombre");
                     
        Button valider = new Button("Valider");
        valider.setOnAction(event5 -> {
            h = Double.parseDouble(text.getText());
            fenetreniveau.close();
            Principale.listeNiveau.get(n).hauteurSousPlafond=h;});
        grid.add(lab, 0, 0);
        grid.add(text, 1, 0);
        grid.add(valider, 0, 3, 2, 1);
        Scene scene = new Scene(grid);
        fenetreniveau.setScene(scene);
        fenetreniveau.showAndWait();}
    private void fenetreouvrir () {
        Stage fenetreouvrir = new Stage();
        fenetreouvrir.setTitle("Ouvrir un fichier");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label lab = new Label("Chemin d'acces du fichier");       
        TextField text = new TextField();      
                     
        Button valider = new Button("Valider");
        valider.setOnAction(event5 -> {
            cheminacces = text.getText();
            fenetreouvrir.close();});
        grid.add(lab, 0, 0);
        grid.add(text, 1, 0);
        grid.add(valider, 0, 3, 2, 1);
        Scene scene = new Scene(grid);
        fenetreouvrir.setScene(scene);
        fenetreouvrir.showAndWait();}
    private void fenetreenregistrer () {
        Stage fenetre = new Stage();
        fenetre.setTitle("Enregistrer le fichier");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label lab = new Label("Nom du fichier");       
        TextField text = new TextField();      
                     
        Button valider = new Button("Valider");
        valider.setOnAction(event5 -> {
            nomDeSauvegarde = text.getText();
            fenetre.close();});
        grid.add(lab, 0, 0);
        grid.add(text, 1, 0);
        grid.add(valider, 0, 3, 2, 1);
        Scene scene = new Scene(grid);
        fenetre.setScene(scene);
        fenetre.showAndWait();}
    private void fenetredevis() {
        Stage fenetre = new Stage();
        fenetre.setTitle("Devis");
        
        StackPane message = new StackPane();
        Batiment batdevis = Principale.listeBatiment.get(0);
        String textedevis = batdevis.devisBatiment();
        Label labeldevis = new Label(textedevis);
        message.getChildren().add(labeldevis);
     
        Scene scene = new Scene(message);
        fenetre.setScene(scene);
        fenetre.showAndWait();}
    public static void main (String[] args) {
        launch(args);
    }
}