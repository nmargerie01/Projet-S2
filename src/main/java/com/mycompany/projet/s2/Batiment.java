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
    
    public void sauvegarder(){
        String sauvegarde = "";
        sauvegarde += this.toString()+"\n";
        for (int i=0;i<=this.listeNiveaux.size();i++){
            sauvegarde += this.listeNiveaux.get(i).toString()+"\n";
            Niveau n = this.listeNiveaux.get(i);
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
        try {
            fichier.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            writer.write(sauvegarde);}

        catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());}}

    public static void lireBatiment(){
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
                        listerevet.add(r);}
                    Sol s = new Sol(id, liste,listerevet);
                    Principale.listeSol.add(s);}
                
                else if (objet.contains("Plafond")){
                    int id = Integer.parseInt(parties[1]);
                    ArrayList<Coin> liste = new ArrayList<>();
                    for (int k=2; k<=5; k++){                           //Ligne à changer si moyen de reperer le changement entre coin et revetement
                        Coin c = Principale.recherchecoin(k);
                        liste.add(c);}
                    ArrayList<Revetement> listerevet = new ArrayList<>();
                    for (int z=6; z<=n; z++){
                        Revetement r = Principale.rechercherevetement(z);
                        listerevet.add(r);}
                    Plafond p = new Plafond(id, liste,listerevet);
                    Principale.listePlafond.add(p);}
                
                else if (objet.contains("Piece")){
                    int id = Integer.parseInt(parties[1]);
                    Sol s = Principale.recherchesol(Integer.parseInt(parties[2]));
                    Plafond p = Principale.rechercheplafond(Integer.parseInt(parties[3]));
                    ArrayList<Mur> liste = new ArrayList<>();
                    for (int k=4; k<=n; k++){                        
                        Mur m = Principale.recherchemur(k);
                        liste.add(m);}
                    Piece piece = new Piece(id,s,p,liste);
                    Principale.listePiece.add(piece);}
                
                else if (objet.contains("Appartement")){
                    int id = Integer.parseInt(parties[1]);
                    int niveau = Integer.parseInt(parties[2]);
                    ArrayList<Piece> liste = new ArrayList<>();
                    for (int k=3; k<=n; k++){                        
                        Piece p = Principale.recherchepiece(k);
                        liste.add(p);}
                    Appartement a = new Appartement(id,niveau,liste);
                    Principale.listeAppartement.add(a);}
                
                else if (objet.contains("Niveau")){
                    int id = Integer.parseInt(parties[1]);
                    double hsp = Double.parseDouble(parties[2]);
                    ArrayList<Appartement> liste = new ArrayList<>();
                    for (int k=3; k<=n; k++){                        
                        Appartement p = Principale.rechercheappartement(k);
                        liste.add(p);}
                    Niveau niv = new Niveau (id,hsp,liste);
                    Principale.listeNiveau.add(niv);}
                
                else if (objet.contains("Batiment")){
                    int id = Integer.parseInt(parties[1]);
                    ArrayList<Niveau> liste = new ArrayList<>();
                    for (int k=2; k<=n; k++){                        
                        Niveau ni = Principale.rechercheniveau(k);
                        liste.add(ni);}
                    Batiment bat = new Batiment (id,liste);
                    Principale.listeBatiment.add(bat);}}}
                
        catch (FileNotFoundException e){
            System.out.println("Erreur : le fichier n’existe pas! " + e);} 
        catch (IOException err){
            System.out.println("Erreur de lecture du fichier: " + err);}}

    public String devisBatiment(){
        double devis = 0;
        for (int i=0;i<this.listeNiveaux.size();i++){     
            devis += this.listeNiveaux.get(i).montantRevetement();}
        return "Prix total du batiment : "+devis+ " €";}
}
