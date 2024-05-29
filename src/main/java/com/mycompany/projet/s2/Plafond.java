package com.mycompany.projet.s2;

import static com.mycompany.projet.s2.Piece.listemurs;
import java.util.ArrayList;

public class Plafond {
    int idPlafond;
    Coin coin1;
    Coin coin2;
    Coin coin3;
    Coin coin4;
    ArrayList<Revetement> revetplafond = new ArrayList<>();

    Plafond (int id,Coin a, Coin b, Coin c, Coin d, ArrayList<Revetement> revetplafond){
        this.idPlafond=id;
        this.coin1=a; 
        this.coin2=b;
        this.coin3=c;
        this.coin4=d;
        this.revetplafond=revetplafond;} 

   
    public void afficher() {
        System.out.print ("Plafond;" +idPlafond +
                ";" + coin1.idCoin + 
                ";" + coin2.idCoin +
                ";" + coin3.idCoin +
                ";" + coin4.idCoin);
        for (int k=0;k <= revetplafond.size();k++){
                System.out.print(";" + revetplafond.get(k).idRevetement);}
    }
    public double surface() {
        double l,L,surface;
        l=Math.sqrt(((this.coin1.x-this.coin2.x)*(this.coin1.x-this.coin2.x))+((this.coin1.y-this.coin2.y)*(this.coin1.y-this.coin2.y)));
        L=Math.sqrt(((this.coin3.x-this.coin2.x)*(this.coin3.x-this.coin2.x))+((this.coin3.y-this.coin2.y)*(this.coin3.y-this.coin2.y)));
        surface=l*L;
        return surface;}
    
    public double montantrevetement(){
        int taille = this.revetplafond.size();
        double montant = 0;
        for (int i = 1; i<=taille ; i++){
            montant = montant + this.revetplafond.get(i).idRevetement*this.surface();}
        return montant;}
}
