package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Niveau {
    int idNiveau;
    double hauteurSousPlafond;
    static ArrayList <Appartement> listeAppartements  = new ArrayList<>();

    Niveau (int idNiveau, double hauteurSousPlafond, ArrayList<Appartement> listeAppartements){
        this.idNiveau=idNiveau;
        this.hauteurSousPlafond=hauteurSousPlafond;
        this.listeAppartements=listeAppartements;} 

    public void afficher() {
        String listeAppartementsid = "";
        for (int i=0; i<= this.listeAppartements.size(); i++){
            listeAppartementsid += ";"+this.listeAppartements.get(i).idAppartement;}
        System.out.println ("Niveau;" +
                idNiveau +";" + 
                hauteurSousPlafond + 
                listeAppartementsid);}
    
    public double surface(){
        double surface = 0;
        for (int i=0;i<this.listeAppartements.size();i++){     
            surface = surface + this.listeAppartements.get(i).surface();}
        return surface;}
    
    public double montantRevetement(){
        double montant = 0;
        for (int i=0;i<this.listeAppartements.size();i++){     
            montant += this.listeAppartements.get(i).montantRevetement();}
        return montant;}
    
    /*public void rewritelevel(){
        for ( Appart ap : this.appartements){
            for (Piece pi : ap.pieces){
                for (int k=0; k<= pi.listemurs.size();k++){
                    Mur mu = Principale.recherchemur(pi.listemurs.get(k)); 
                    Coin cd = mu.CoinDebut;
                    Coin cf = mu.CoinFin;
                    double x = cd.x;
                    double y = cd.y;
                    double w = cf.x;
                    double z = cf.y;
                    Circle circle = new Circle(x, y, 2, Color.LIGHTGRAY);
                    root.getChildren().add(circle);
                    Circle circle2 = new Circle(w, z, 2, Color.LIGHTGRAY);
                    root.getChildren().add(circle2);
                    Line line = new Line(x, y, w, z);
                    line.setStyle("-fx-stroke: Colors.LIGHTGRAY;");
                    root.getChildren().add(line);
                }
            }
        }
    }*/
}
