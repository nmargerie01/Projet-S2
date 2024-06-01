package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Mur {
    int idMur;
    Coin CoinDebut;
    Coin CoinFin;
    int nbreFenetres;
    int nbrePortes;
    ArrayList<Revetement> listeRevetements = new ArrayList<>();
    
    Mur (int idMur,Coin CoinDebut,Coin CoinFin,int nbreFenetres,int nbrePortes, ArrayList<Revetement> listeRevetements){
        this.idMur=idMur;
        this.CoinDebut=CoinDebut;
        this.CoinFin=CoinFin;
        this.nbreFenetres=nbreFenetres;
        this.nbrePortes=nbrePortes;
        this.listeRevetements=listeRevetements;} 
    
    @Override
    public String toString() {
        return "Mur;"+idMur+";"+CoinDebut.idCoin+";"+CoinFin.idCoin+";"+nbreFenetres+";"+nbrePortes+";"+listeRevetements;}
    
    public void afficher() {
        String listeRevetementsid = "";
        for (int i=0; i<= listeRevetements.size(); i++){
            listeRevetementsid=listeRevetementsid+listeRevetements.get(i)+";";}
        System.out.println ("Mur;"+idMur+";"+CoinDebut.idCoin+";"+CoinFin.idCoin+";"+nbreFenetres+";"+nbrePortes+";"+listeRevetementsid);}
    
    public double surface(){   
        double x1 = this.CoinDebut.x;
        double y1 = this.CoinDebut.y;
        double x2 = this.CoinFin.x;
        double y2 = this.CoinFin.y;
        double d=Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
        double f = App.p1;
        double p = App.p2;
        double h = App.h ;
        double surface=((d*h)-((f*1.44)+(p*1.68)));
        surface=surface*(2/100);
        return surface;}}
    
    /*public double montantrevetement(){
        double montant = this.revetmur.prixUnitaire*this.surface();
        return montant;}
}*/
