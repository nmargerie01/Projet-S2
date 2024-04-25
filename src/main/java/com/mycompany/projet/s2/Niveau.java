package com.mycompany.projet.s2;

import java.util.ArrayList;

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
}
