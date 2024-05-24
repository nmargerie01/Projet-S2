package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Appart {
    int idAppart;
    int idNiveauAppartement;
    ArrayList<Piece> pieces = new ArrayList<> ();
    
    Appart (int id, int niveau, ArrayList<Piece> pieces){
        this.idAppart = id;
        this.idNiveauAppartement = niveau;
        this.pieces = pieces;}
    
    public void afficher(){
        System.out.println ("Appart("+idAppart+","+idNiveauAppartement+","+pieces+')');}
    
    public double surface(){
        double surface = 0;
        for (int i=0;i<this.pieces.size();i++){     
            surface = surface + this.pieces.get(i).surface();}
        return surface;}
    
    public double montantrevetement(){
        double montant = 0;
        for (int i=0;i<this.pieces.size();i++){     
            montant = montant + this.pieces.get(i).montantrevetement();}
        return montant;}
}
