package com.mycompany.projet.s2;

import java.util.ArrayList;
import javafx.scene.shape.Line;

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
        String listeRevetementsid = "";
        for (int i=0; i<= this.listeRevetements.size(); i++){
            listeRevetementsid += ";"+this.listeRevetements.get(i).idRevetement;}
        return "Mur;"+
                idMur+";"+
                CoinDebut.idCoin+";"+
                CoinFin.idCoin+";"+
                nbreFenetres+";"+
                nbrePortes+
                listeRevetementsid;}
    
    public void afficher() {
    Line line = new Line(this.CoinDebut.x, this.CoinDebut.y, this.CoinFin.x, this.CoinFin.y);
        App.root.getChildren().add(line);}
    
    public double surface(){   
        double x1 = this.CoinDebut.x;
        double y1 = this.CoinDebut.y;
        double x2 = this.CoinFin.x;
        double y2 = this.CoinFin.y;
        double d=Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)));
        double f = Integer.parseInt(App.p1);
        double p = Integer.parseInt(App.p2);
        double h = App.h ;
        double surface=((d*h)-((f*1.44)+(p*1.68)));
        surface=surface*(2/100);
        return surface;}
    
    public double montantRevetement(){
        double prixRevetementtotal = 0;
        for (int i=0; i<= this.listeRevetements.size(); i++){
            prixRevetementtotal=prixRevetementtotal+this.listeRevetements.get(i).prixUnitaire;} 
        double montant = prixRevetementtotal*this.surface();
        return montant;}}
