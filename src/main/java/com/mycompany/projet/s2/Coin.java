package com.mycompany.projet.s2;

public class Coin {
    int idCoin;
    double x, y;
    
    Coin (int id,double x, double y){
        this.idCoin=id;
        this.x=x;
        this.y=y;}
    
    @Override
    public String toString(){
        return "Coin;"+idCoin+";"+x+";"+y;}

    public void afficher(){
        System.out.println("Coin;"+idCoin+";"+x+";"+y);}}
