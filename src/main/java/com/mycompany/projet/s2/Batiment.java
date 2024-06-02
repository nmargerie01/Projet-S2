package com.mycompany.projet.s2;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Batiment {
    int idBatiment;
    ArrayList <Niveau> listeNiveaux = new ArrayList<>();

    Batiment (int idBatiment, ArrayList<Niveau> listeNiveaux){
        this.idBatiment=idBatiment;
        this.listeNiveaux=listeNiveaux;} 
    
    @Override
    public String toString(){
        String listeNiveauxid = "";
        for (int i=0; i<= this.listeNiveaux.size(); i++){
            listeNiveauxid += ";"+this.listeNiveaux.get(i).idNiveau;}
        return "Batiment;"+idBatiment+listeNiveauxid;}
    
    public void afficher(){
        String listeNiveauxid = "";
        for (int i=0; i<= this.listeNiveaux.size(); i++){
            listeNiveauxid += ";"+this.listeNiveaux.get(i).idNiveau;}
        System.out.println ("Batiment;" +
                idBatiment +
                listeNiveauxid);}
    
    public void sauvegarder() throws IOException{
        String sauvegarde = "";
        sauvegarde += this.toString()+"\n";
        for (int i=0;i<=listeNiveaux.size();i++){
            sauvegarde += listeNiveaux.get(i).toString()+"\n";
            Niveau n = listeNiveaux.get(i);
            for (int j=0;j<=n.listeAppartements.size();j++){
                sauvegarde += n.listeAppartements.get(j).toString()+"\n";
                Appartement a = n.listeAppartements.get(j);
                for (int k=0;k<=a.listePieces.size();k++){
                    sauvegarde += a.listePieces.get(k).toString()+"\n";
                    Piece p = a.listePieces.get(k);
                    sauvegarde += a.listePieces.get(k).sol.toString()+"\n";
                    sauvegarde += a.listePieces.get(k).plafond.toString()+"\n";
                    for (int l=0;l<=p.listeMurs.size();l++){
                        sauvegarde += p.listeMurs.get(l).toString()+"\n";
                        sauvegarde += p.listeMurs.get(l).CoinDebut.toString()+"\n";
                        sauvegarde += p.listeMurs.get(l).CoinFin.toString()+"\n";}}}}
                        
        File fichier = new File(App.nomDeSauvegarde);
        fichier.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(sauvegarde);}

        catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());}}

    public void lireBatiment(){
         List<String> lignes = new ArrayList<>();

        try {
            File csvFile = new File(App.cheminacces);
            FileReader fr = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lignes.add(line);}
            Collections.reverse(lignes);

            for (String ligne : lignes) {
                String[] parties = ligne.split(";");
                int n = parties.length;
                String objet = parties[0];
                        
                if (objet.contains("Coin")){
                    int id = Integer.parseInt(parties[1]);
                    double x = Double.parseDouble(parties[2]);
                    double y = Double.parseDouble(parties[3]);
                    Coin c = new Coin(id, x, y);
                    Principale.listeCoin.add(c);}

                else if (objet.contains("Mur")){
                    int id = Integer.parseInt(parties[1]);
                    Coin debut = Principale.recherchecoin(Integer.parseInt(parties[2]));
                    Coin fin = Principale.recherchecoin(Integer.parseInt(parties[3]));
                    int fenetre = Integer.parseInt(parties[4]);
                    int porte = Integer.parseInt(parties[5]);
                    ArrayList<Revetement> liste = new ArrayList<>();
                    for (int k=6; k<=n; k++){
                        Revetement r = Principale.rechercherevetement(k);
                        liste.add(r);}
                    Mur m = new Mur(id, debut, fin, fenetre, porte,liste);
                    Principale.listeMur.add(m);}

                else if (objet.contains("Sol")){
                    int id = Integer.parseInt(parties[1]);
                    ArrayList<Coin> liste = new ArrayList<>();
                    for (int k=2; k<=5; k++){                           //Ligne à changer si moyen de reperer le changement entre coin et revetement
                        Coin c = Principale.recherchecoin(k);
                        liste.add(c);}
                    ArrayList<Revetement> listerevet = new ArrayList<>();
                    for (int z=6; z<=n; z++){
                        Revetement r = Principale.rechercherevetement(z);
                        liste.add(r);}
                    Sol s = new Sol(id, liste,listerevet);
                    Principale.listeSol.add(s);}
                
                Revetement r = new Revetement(id, nom, mur, sol, plafond, prix);
                r.afficher();
                Principale.listeRevetement.add(r);}}
    
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n’existe pas! " + e);} 
        catch (IOException err){
            System.out.println("Erreur de lecture du fichier: " + err);}}}
    devisBatiment()
    
    
    
    /*public double devisbatiment(){
        double devis = 0;
        for (int i=0;i<this.niveaux.size();i++){     
            devis = devis + this.niveaux.get(i).montantrevetement();}
        return devis;}*/
}
