package com.mycompany.projet.s2;

import java.io.*;

public class Revetement {
    int idRevetement;
    String designation;
    boolean pourMur;
    boolean pourSol;
    boolean pourPlafond;
    double prixUnitaire;

    Revetement(int id, String nom, boolean mur, boolean sol, boolean plafond, double prix) {
        this.idRevetement = id;
        this.designation = nom;
        this.pourMur = mur;
        this.pourSol = sol;
        this.pourPlafond = plafond;
        this.prixUnitaire = prix;}

    public void main(String[] args) {
        try { 
            File csvFile = new File("CatalogueRevetements.txt");
            FileReader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr); 
            String line;
            while ((line = br.readLine()) != null) {
                String[] parties = line.split(";");
                int id = Integer.parseInt(parties[0]);
                String nom = parties[1];
                boolean mur = false;
                if (Integer.valueOf(parties[2]) == 1){
                    mur = true;}
                boolean sol = false;
                if (Integer.valueOf(parties[3]) == 1){
                    sol = true;}
                boolean plafond = false;
                if (Integer.valueOf(parties[4]) == 1){
                    plafond = true;}
                double prix = Double.parseDouble(parties[5]);

                Revetement r = new Revetement(id, nom, mur, sol, plafond, prix);
                Principale.listeRevetement.add(r);}} 
    
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n’existe pas! " + e);} 
    
        catch (IOException err){
            System.out.println("Erreur de lecture du fichier: " + err);}}
    

    @Override
    public String toString() {
        return "Revetement{" +
                "idRevetement=" + idRevetement +
                ", designation='" + designation + '\'' +
                ", pourMur=" + pourMur +
                ", pourSol=" + pourSol +
                ", pourPlafond=" + pourPlafond +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
