package com.mycompany.projet.s2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
        Circle circle = new Circle(this.x, this.y, 2, Color.BLACK);
        App.root.getChildren().add(circle);}}
