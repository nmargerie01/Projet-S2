package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Appartement {
    int idAppartement;
    int idNiveauAppartement;
    ArrayList<Piece> listePieces = new ArrayList<> ();
    
    Appartement (int idAppartement, int idNiveauAppartement, ArrayList<Piece> listePieces){
        this.idAppartement = idAppartement;
        this.idNiveauAppartement = idNiveauAppartement;
        this.listePieces = listePieces;}
    
    public void afficher(){
        String listePiecesid = "";
        for (int i=0; i<= this.listePieces.size(); i++){
            listePiecesid += ";"+this.listePieces.get(i).idPiece;}
        System.out.println ("Appartement;"+
                idAppartement+";"+
                idNiveauAppartement+
                listePiecesid);}
    
    public double surface(){
        double surface = 0;
        for (int i=0;i<this.listePieces.size();i++){     
            surface += this.listePieces.get(i).surface();}
        return surface;}
    
    public double montantRevetement(){
        double montant = 0;
        for (int i=0;i<this.listePieces.size();i++){     
            montant = montant + this.listePieces.get(i).montantRevetement();}
        return montant;}
}
