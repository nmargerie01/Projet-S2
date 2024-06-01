package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Sol {
    int idSol;
    ArrayList<Coin> listeCoins = new ArrayList<>();
    ArrayList<Revetement> listeRevetements = new ArrayList<>();

    Sol(int idSol,ArrayList<Coin> listeCoins, ArrayList<Revetement> listeRevetements){
        this.idSol=idSol;
        this.listeCoins=listeCoins;
        this.listeRevetements=listeRevetements;} 
   
    public void afficher() {
        String listeCoinsid = "";
        String listeRevetementsid = "";
        for (int i=0; i<= this.listeCoins.size(); i++){
            listeCoinsid += ";"+this.listeCoins.get(i).idCoin;}
        for (int i=0; i<= this.listeRevetements.size(); i++){
            listeRevetementsid += ";"+this.listeRevetements.get(i).idRevetement;}
        System.out.print ("Sol;" +idSol +
                listeCoinsid+
                listeRevetementsid);}
    
    public double surface() {
        int pixelsParMetre = 50;
        if (this.listeCoins == null || this.listeCoins.isEmpty()) {
            return 0.0;}
        double surface = 0.0;
        int n = this.listeCoins.size();
        for (int i = 0; i < n; i++) {
            Coin c1 = this.listeCoins.get(i);
            Coin c2 = this.listeCoins.get((i + 1) % n);
            surface += (c1.x* c2.y) - (c2.x * c1.y);}
        surface = Math.abs(surface) / 2.0;
        return surface / (pixelsParMetre * pixelsParMetre);}
    
    public double montantRevetement() {
        double prixRevetementtotal = 0;
        for (int i=0; i<= this.listeRevetements.size(); i++){
            prixRevetementtotal=prixRevetementtotal+this.listeRevetements.get(i).prixUnitaire;} 
        double montant = prixRevetementtotal*this.surface();
        return montant;}}
