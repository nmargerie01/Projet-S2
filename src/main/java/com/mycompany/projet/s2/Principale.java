package com.mycompany.projet.s2;

import java.util.ArrayList;

public class Principale {

    static ArrayList <Coin> listeCoin = new ArrayList<>();
    static ArrayList <Mur> listeMur = new ArrayList<>();
    static ArrayList <Sol> listeSol = new ArrayList<>();
    static ArrayList <Plafond> listePlafond = new ArrayList<>();
    static ArrayList <Piece> listePiece = new ArrayList<>();
    static ArrayList <Appart> listeAppart = new ArrayList<>();
    static ArrayList <Niveau> listeNiveau = new ArrayList<>();
    static ArrayList <Batiment> listeBatiment = new ArrayList<>();
    static ArrayList <Revetement> listeRevetement = new ArrayList<>();


    public void creationcoin (){
        System.out.println("Identifiant:");
        int idc=Lire.i();
        System.out.println("Abscisse:");
        double ac=Lire.d();
        System.out.println("Ordonnée:");
        double bc=Lire.d();
        
        Coin c = new Coin(idc,ac,bc);
        listeCoin.add(c);}
        
    public void creationsol (){
        System.out.println("Identifiant:");
        int ids=Lire.i();
        System.out.println("Id du premier coin:");
        int idas=Lire.i();
        Coin as = recherchecoin(idas);
        System.out.println("Id du deuxième coin:");
        int idbs=Lire.i();
        Coin bs = recherchecoin(idbs);
        System.out.println("Id du troisieme coin:");
        int idcs=Lire.i();
        Coin cs = recherchecoin(idcs);
        System.out.println("Id du quatrieme coin:");
        int idds=Lire.i();
        Coin ds = recherchecoin(idds);
        System.out.println("Quel revetement (donner son Id) ?");
        int idrevetsol=Lire.i();
        Revetement revets = rechercherevetement(idrevetsol);
     
        Sol s = new Sol(ids, as, bs, cs, ds, revets);
        listeSol.add(s);}
    
    public void creationmur (){
        System.out.println("Identifiant:");
        int idm=Lire.i();
        System.out.println("Id du premier coin du mur:");
        int idam=Lire.i();
        Coin am = recherchecoin(idam);
        System.out.println("Id du deuxième coin du mur");
        int idbm=Lire.i();
        Coin bm = recherchecoin(idbm);
        System.out.println("Combien de fenetres sur ce mur ?");
            int fm=Lire.i();
        System.out.println("Combien de porte sur ce mur ?");
            int pm=Lire.i();
        System.out.println("Quel revetement (donner son Id) ?");
        int idrevetmur=Lire.i();
        Revetement revetmur = rechercherevetement(idrevetmur);
        
        Mur m = new Mur(idm, am, bm, fm, pm, revetmur);
        listeMur.add(m);}
    
   public void creationplafond (){
        System.out.println("Identifiant:");
        int idp=Lire.i();
        System.out.println("Id du premier coin:");
        int idap=Lire.i();
        Coin ap = recherchecoin(idap);
        System.out.println("Id du deuxième coin:");
        int idbp=Lire.i();
        Coin bp = recherchecoin(idbp);
        System.out.println("Id du troisieme coin:");
        int idcp=Lire.i();
        Coin cp = recherchecoin(idcp);
        System.out.println("Id du quatrieme coin:");
        int iddp=Lire.i();
        Coin dp = recherchecoin(iddp);
        System.out.println("Quel revetement (donner son Id) ?");
        int idrevetplafond=Lire.i();
        Revetement revetp = rechercherevetement(idrevetplafond);
     
        Plafond p = new Plafond(idp, ap, bp, cp, dp, revetp);
        listePlafond.add(p);} 
   
    public void creationpiece (){
        System.out.println("Identifiant:");
        int idp=Lire.i();
        System.out.println("Id du sol:");
        int idsol=Lire.i();
        Sol sol = recherchesol(idsol);
        System.out.println("Id du plafond:");
        int idplafond =Lire.i();
        Plafond plafond = rechercheplafond(idplafond);
        boolean m = true;
        ArrayList<Mur> listemurs = new ArrayList<>();
        while (m == true){
            System.out.println("Ajouter un mur a la piece (avec son id):");
            int id = Lire.i();
            Mur mur = recherchemur(id);
            listemurs.add(mur);
            System.out.println("Un autre ?");
            int reponse = Lire.i();
            if (reponse == 0){
                m = false;}}
              
        Piece p = new Piece (idp, sol, plafond, listemurs);
        listePiece.add(p);}
    
    public void creationappart (){
        System.out.println("Identifiant:");
        int ida=Lire.i();
        System.out.println("Identifiant du niveau:");
        int idniveau=Lire.i();
        boolean p = true;
        ArrayList<Piece> listepieces = new ArrayList<>();
        while (p == true){
            System.out.println("Ajouter une piece a l'appart (avec son id):");
            int id = Lire.i();
            Piece piece = recherchepiece(id);
            listepieces.add(piece);
            System.out.println("Une autre ?");
            int reponse = Lire.i();
            if (reponse == 0){
                p = false;}}
        
        Appart a = new Appart (ida, idniveau, listepieces);
        listeAppart.add(a);}
    
    public void creationniveau (){
        System.out.println("Identifiant:");
        int idn=Lire.i();
        System.out.println("Hauteur sous plafond :");
        double hsp=Lire.d();
        boolean a = true;
        ArrayList<Appart> listeapparts = new ArrayList<>();
        while (a == true){
            System.out.println("Ajouter un appart au niveau (avec son id):");
            int id = Lire.i();
            Appart appart = rechercheappart(id);
            listeapparts.add(appart);
            System.out.println("Un autre ?");
            int reponse = Lire.i();
            if (reponse == 0){
                a = false;}}
        
        Niveau n = new Niveau (idn, hsp, listeapparts);
        listeNiveau.add(n);}
    
    public Coin recherchecoin (int id) {
        for (Coin coin : listeCoin) {
            if (coin.idCoin == id) {
                return coin;}}
        return null;}
    
    public static Coin recherchecoinparcoordonnee (double x, double y) {
        for (Coin coin : listeCoin) {
            if (coin.x == x && coin.y == y) {
                return coin;}}
        return null;}
    
    public Mur recherchemur (int id) {
        for (Mur mur : listeMur) {
            if (mur.idMur == id) {
                return mur;}}
        return null;}
    
        public static Mur recherchemurparcoordonnee (double x, double y,double w, double z){
        for (Mur mur : listeMur) {
            if ((mur.CoinDebut.x == x) && (mur.CoinDebut.y == y) && (mur.CoinFin.x == w) && (mur.CoinFin.y == z))
                return mur;}
        return null;}
    
    public Sol recherchesol (int id) {
        for (Sol sol : listeSol) {
            if (sol.idSol == id) {
                return sol;}}
        return null;}
    
    public static Revetement rechercherevetement (int id) {
        for (Revetement revetement : listeRevetement) {
            if (revetement.idRevetement == id) {
                return revetement;}}
        return null;}
    
    public Plafond rechercheplafond (int id) {
        for (Plafond plafond : listePlafond) {
            if (plafond.idPlafond == id) {
                return plafond;}}
        return null;}
    
    public Piece recherchepiece (int id) {
        for (Piece piece : listePiece) {
            if (piece.idPiece == id) {
                return piece;}}
        return null;}
    
    public Appart rechercheappart (int id) {
        for (Appart appart : listeAppart) {
            if (appart.idAppart == id) {
                return appart;}}
        return null;}
    
    public Niveau rechercheniveau (int id) {
        for (Niveau niveau : listeNiveau) {
            if (niveau.idNiveau == id) {
                return niveau;}}
        return null;}
}
