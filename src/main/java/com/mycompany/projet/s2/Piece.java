package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Piece {
    int idPiece;
    Sol sol;
    Plafond plafond;
    ArrayList<Mur> listemurs = new ArrayList<>();    
    
    Piece (int id, Sol sol, Plafond plafond, ArrayList listemurs){
        this.idPiece = id;
        this.sol = sol;
        this.plafond = plafond;
        this.listemurs=listemurs;}
    
    public double surface() {
        /*for 
        double largeur =  Math.sqrt(((this.sol.coin1.x-this.sol.coin2.x)*(this.sol.coin1.x-this.sol.coin2.x))+((this.sol.coin1.y-this.sol.coin2.y)*(this.sol.coin1.y-this.sol.coin2.y)));
        double longueur = Math.sqrt(((this.sol.coin3.x-this.sol.coin2.x)*(this.sol.coin3.x-this.sol.coin2.x))+((this.sol.coin3.y-this.sol.coin2.y)*(this.sol.coin3.y-this.sol.coin2.y)));
        double surface=longueur * largeur;*/
        double surface =3;
        return surface;}
    
   /* public double montantrevetement(){
        double montantsolplafond = this.sol.montantrevetement()+this.plafond.montantrevetement();
        double montantmurs = 0;
        for (int i=0;i<this.listemurs.size();i++){     
            montantmurs = montantmurs + this.listemurs.get(i).montantrevetement();}
        double montant = montantmurs + montantsolplafond;
        return montant;}*/
    
    public void afficher(){
        System.out.print ("Piece;" +idPiece +
                ";" + sol.idSol + 
                ";" + plafond.idPlafond);
        for (int k = 0; k<= listemurs.size();k++){
            System.out.print (";" + listemurs.get(k).idMur);}
        }
}
