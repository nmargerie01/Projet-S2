package com.mycompany.projet.s2;

public class Plafond {
    int idPlafond;
    Coin coin1;
    Coin coin2;
    Coin coin3;
    Coin coin4;
    Revetement revplafond;

    Plafond (int id,Coin a, Coin b, Coin c, Coin d, Revetement revplafond){
        this.idPlafond=id;
        this.coin1=a; 
        this.coin2=b;
        this.coin3=c;
        this.coin4=d;
        this.revplafond=revplafond;} 

   
    public void afficher() {
        System.out.println ("Plafond;" +idPlafond +
                ";" + coin1.idCoin + 
                ";" + coin2.idCoin +
                ";" + coin3.idCoin +
                ";" + coin4.idCoin +
                ";" + revplafond);
    }
    public double surface() {
        double l,L,surface;
        l=Math.sqrt(((this.coin1.x-this.coin2.x)*(this.coin1.x-this.coin2.x))+((this.coin1.y-this.coin2.y)*(this.coin1.y-this.coin2.y)));
        L=Math.sqrt(((this.coin3.x-this.coin2.x)*(this.coin3.x-this.coin2.x))+((this.coin3.y-this.coin2.y)*(this.coin3.y-this.coin2.y)));
        surface=l*L;
        return surface;}
    
    public double montantrevetement(){
        double montant = this.revplafond.prixUnitaire*this.surface();
        return montant;}
}
