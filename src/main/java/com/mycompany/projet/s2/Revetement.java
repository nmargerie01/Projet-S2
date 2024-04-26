package com.mycompany.projet.s2;


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
    
    public String afficherlegende() {
        return idRevetement+" | " + designation + " - " + prixUnitaire;
    }
    public String afficher() {
        System.out.println("Revetement(" + idRevetement +
                "," + designation + 
                "," + pourMur +
                "," + pourSol +
                "," + pourPlafond +
                "," + prixUnitaire +
                ')');
    }}
