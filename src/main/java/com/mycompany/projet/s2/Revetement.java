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
    
    public String afficherlegende() {
        return idRevetement+" | " + designation + " - " + prixUnitaire + " €/m²";
    }
    public void afficher() {
        System.out.println("Revetement;" + idRevetement +
                ";" + designation + 
                ";" + pourMur +
                ";" + pourSol +
                ";" + pourPlafond +
                ";" + prixUnitaire);
    }
}
