package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Batiment {
    int idBatiment;
    ArrayList <Niveau> niveaux = new ArrayList<>();

    Batiment (int id, ArrayList<Niveau> listedesniveaux){
        this.idBatiment=id;
        this.niveaux=listedesniveaux;} 
    
    public double devisbatiment(){
        double devis = 0;
        for (int i=0;i<this.niveaux.size();i++){     
            devis = devis + this.niveaux.get(i).montantrevetement();}
        return devis;}
}
