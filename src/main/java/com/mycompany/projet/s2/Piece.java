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
    
    public void afficher(){
        String listeMursid = "";
        for (int i=0; i<= this.listeMurs.size(); i++){
            listeMursid += ";"+listeMurs.get(i).idMur;}
        System.out.println ("Piece;"+
                idPiece+";"+
                sol+";"+
                plafond+
                listeMursid);}
    
    public double surface() {
        return sol.surface();}
        
   public double montantRevetement(){
        double montant = this.sol.montantRevetement()+this.plafond.montantRevetement();
        for (int i=0;i<this.listeMurs.size();i++){     
            montant += this.listeMurs.get(i).montantRevetement();}
        return montant;}
    
    
}
