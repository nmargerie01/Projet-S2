package com.mycompany.projet.s2;

public class Mur {
    int idMur;
    Coin CoinDebut;
    Coin CoinFin;
    int nbFenetre;
    int nbPorte;
    Revetement revetmur;
    
    Mur (int id,Coin a,Coin b,int f,int p, Revetement revetmur){
        this.idMur=id;
        this.CoinDebut=a;
        this.CoinFin=b;
        this.nbFenetre=f;
        this.nbPorte=p;
        this.revetmur=revetmur;} 
    
    
    public void afficher() {
        System.out.println ("Mur(" +idMur +
                "," + CoinDebut + 
                "," + CoinFin +
                "," + nbFenetre +
                "," + nbPorte +
                "," + revetmur +
                ')');}
    
    public double surface(){    
        double d=Math.sqrt(((App.x1-App.x2)*(App.x1-App.x2))+((App.y1-App.y2)*(App.y1-App.y2)));
        double surface=((d*h)-((f*1.44)+(p*1.68)));
        return surface/10;}}}}}
        return 0;}
    
    public double montantrevetement(){
        double montant = this.revetmur.prixUnitaire*this.surface();
        return montant;}
}
