package com.mycompany.projet.s2;

public class Sol {
   int idSol;
    Coin coin1;
    Coin coin2;
    Coin coin3;
    Coin coin4;
    Revetement revsol;

    Sol (int id,Coin a, Coin b, Coin c, Coin d, Revetement revsol){
        this.idSol=id;
        this.coin1=a; 
        this.coin2=b;
        this.coin3=c;
        this.coin4=d;
        this.revsol=revsol;} 

        
    public void afficher() {
        System.out.println ("Sol;" +idSol +
                ";" + coin1.idCoin + 
                ";" + coin2.idCoin +
                ";" + coin3.idCoin +
                ";" + coin4.idCoin +
                ";" + revsol);
    }
    
    public double surface() {
        double l,L,surface;
        l=Math.sqrt(((this.coin1.x-this.coin2.x)*(this.coin1.x-this.coin2.x))+((this.coin1.y-this.coin2.y)*(this.coin1.y-this.coin2.y)));
        L=Math.sqrt(((this.coin3.x-this.coin2.x)*(this.coin3.x-this.coin2.x))+((this.coin3.y-this.coin2.y)*(this.coin3.y-this.coin2.y)));
        surface=l*L;
        return surface;}
    
    public double montantrevetement(){
        double montant = this.revsol.prixUnitaire*this.surface();
        return montant;} 
}
