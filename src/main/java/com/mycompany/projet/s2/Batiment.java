package com.mycompany.projet.s2;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Batiment {
    int idBatiment;
    ArrayList <Niveau> listeNiveaux = new ArrayList<>();

    Batiment (int idBatiment, ArrayList<Niveau> listeNiveaux){
        this.idBatiment=idBatiment;
        this.listeNiveaux=listeNiveaux;} 
    
    public void afficher(){
        String listeNiveauxid = "";
        for (int i=0; i<= this.listeNiveaux.size(); i++){
            listeNiveauxid += ";"+this.listeNiveaux.get(i).idNiveau;}
        System.out.println ("Batiment;" +
                idBatiment +
                listeNiveauxid);}
    
    public String sauvegarder(){
        String sauvegarde = "";
        sauvegarde += this.afficher()+"\n";
        for (int i=0;i<=listeNiveaux.size();i++){
            sauvegarde += listeNiveaux.get(i).afficher();
            
        }
        File fichier = new File(App.nomDeSauvegarde);
        fichier.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(sauvegarde);}

        catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());}}

    
    lireBatiment()
    devisBatiment()
    
    
    
    /*public double devisbatiment(){
        double devis = 0;
        for (int i=0;i<this.niveaux.size();i++){     
            devis = devis + this.niveaux.get(i).montantrevetement();}
        return devis;}*/
}
