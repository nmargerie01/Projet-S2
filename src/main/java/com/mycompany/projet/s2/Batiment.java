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
    
    @Override
    public String toString(){
        String listeNiveauxid = "";
        for (int i=0; i<= this.listeNiveaux.size(); i++){
            listeNiveauxid += ";"+this.listeNiveaux.get(i).idNiveau;}
        return "Batiment;"+idBatiment+listeNiveauxid;}
    
    public void afficher(){
        String listeNiveauxid = "";
        for (int i=0; i<= this.listeNiveaux.size(); i++){
            listeNiveauxid += ";"+this.listeNiveaux.get(i).idNiveau;}
        System.out.println ("Batiment;" +
                idBatiment +
                listeNiveauxid);}
    
    public void sauvegarder() throws IOException{
        String sauvegarde = "";
        sauvegarde += this.toString()+"\n";
        for (int i=0;i<=listeNiveaux.size();i++){
            sauvegarde += listeNiveaux.get(i).toString()+"\n";
            Niveau n = listeNiveaux.get(i);
            for (int j=0;j<=n.listeAppartements.size();j++){
                sauvegarde += n.listeAppartements.get(j).toString()+"\n";
                Appartement a = n.listeAppartements.get(j);
                for (int k=0;k<=a.listePieces.size();k++){
                    sauvegarde += a.listePieces.get(k).toString()+"\n";
                    Piece p = a.listePieces.get(k);
                    for (int l=0;l<=p.listeMurs.size();l++){
                        sauvegarde += p.listeMurs.get(l).toString()+"\n";
                        sauvegarde += p.listeMurs.get(l).CoinDebut.toString()+"\n";
                        sauvegarde += p.listeMurs.get(l).CoinFin.toString()+"\n";}}}}
                        
        File fichier = new File(App.nomDeSauvegarde);
        fichier.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(sauvegarde);}

        catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());}}

    /*lireBatiment()
    devisBatiment()
    */
    
    
    /*public double devisbatiment(){
        double devis = 0;
        for (int i=0;i<this.niveaux.size();i++){     
            devis = devis + this.niveaux.get(i).montantrevetement();}
        return devis;}*/
}
