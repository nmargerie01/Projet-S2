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
        double montantsolplafond = this.sol.montantRevetement()+this.plafond.montantRevetement();
        double montantmurs = 0;
        for (int i=0;i<this.listeMurs.size();i++){     
            montantmurs = montantmurs + this.listeMurs.get(i).montantRevetement();}
        double montant = montantmurs + montantsolplafond;
        return montant;}
    
    public void afficher(){
        System.out.print ("Piece;" +idPiece +
                ";" + sol.idSol + 
                ";" + plafond.idPlafond);
        for (int k = 0; k<= listeMurs.size();k++){
            System.out.print (";" + listeMurs.get(k).idMur);}
        }
}
