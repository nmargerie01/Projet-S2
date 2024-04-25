package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Piece {
    int idPiece;
    Sol sol;
    Plafond plafond;
    ArrayList<Mur> listemurs = new ArrayList<>();    
    
    Piece (int id, Sol sol, Plafond plafond, ArrayList<Mur> listemurs){
        this.idPiece = id;
        this.sol = sol;
        this.plafond = plafond;
        this.listemurs=listemurs;}
    
    public double surface() {
        for 
        double largeur =  Math.sqrt(((this.sol.coin1.x-this.sol.coin2.x)*(this.sol.coin1.x-this.sol.coin2.x))+((this.sol.coin1.y-this.sol.coin2.y)*(this.sol.coin1.y-this.sol.coin2.y)));
        double longueur = Math.sqrt(((this.sol.coin3.x-this.sol.coin2.x)*(this.sol.coin3.x-this.sol.coin2.x))+((this.sol.coin3.y-this.sol.coin2.y)*(this.sol.coin3.y-this.sol.coin2.y)));
        double surface=longueur * largeur;
        return surface;}
    
    public double montantrevetement(){
        double montant = this.sol.montantrevetement()+this.plafond.montantrevetement()+this.mur1.montantrevetement()+this.mur2.montantrevetement()+this.mur3.montantrevetement()+this.mur4.montantrevetement();
        return montant;}
}
