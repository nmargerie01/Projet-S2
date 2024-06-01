package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Piece {
    int idPiece;
    Sol sol;
    Plafond plafond;
    ArrayList<Mur> listeMurs = new ArrayList<>();    
    
    Piece (int id, Sol sol, Plafond plafond, ArrayList listeMurs){
        this.idPiece = id;
        this.sol = sol;
        this.plafond = plafond;
        this.listeMurs=listeMurs;}
    
    public double surface() {
        int pixelsParMetre = 50;
        if (this.listeMurs == null || this.listeMurs.isEmpty()) {
            return 0.0;}
        double surface = 0.0;
        int n = this.listeMurs.size();
        for (int i = 0; i < n; i++) {
            Coin c1 = this.listeMurs.get(i).CoinDebut;
            Coin c2 = this.listeMurs.get((i + 1) % n).CoinDebut;
            surface += (c1.x* c2.y) - (c2.x * c1.y);}
        surface = Math.abs(surface) / 2.0;
        return surface / (pixelsParMetre * pixelsParMetre);}
        
   public double montantRevetement(){
        double montant = this.sol.montantRevetement()+this.plafond.montantRevetement();
        for (int i=0;i<this.listeMurs.size();i++){     
            montant += this.listeMurs.get(i).montantRevetement();}
        return montant;}
    
    public void afficher(){
        String listeMursid = "";
        for (int i=0; i<= this.listeMurs.size(); i++){
            listeMursid += ";"+listeMurs.get(i).idMur;}
        System.out.println ("Piece;"+
                idPiece+";"+
                sol+";"+
                plafond+
                listeMursid);}
}
