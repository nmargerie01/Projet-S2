package com.mycompany.projet.s2;

import static com.mycompany.projet.s2.App.root;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class Niveau {
    int idNiveau;
    double hauteurSousPlafond;
    static ArrayList <Appart> appartements = new ArrayList<>();

    Niveau (int id, double hsp, ArrayList<Appart> listedesappartements){
        this.idNiveau=id;
        this.hauteurSousPlafond=hsp;
        this.appartements=listedesappartements;} 
    
    public double surface(){
        double surface = 0;
        for (int i=0;i<this.appartements.size();i++){     
            surface = surface + this.appartements.get(i).surface();}
        return surface;}
    
    public double montantrevetement(){
        double montant = 0;
        for (int i=0;i<this.appartements.size();i++){     
            montant = montant + this.appartements.get(i).montantrevetement();}
        return montant;}
    
    public void rewritelevel(){
        for ( Appart ap : this.appartements){
            for (Piece pi : ap.pieces){
                for (Mur mu : pi.listemurs){
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
    }
}
