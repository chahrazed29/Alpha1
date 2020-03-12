package com.example.alpha;

public abstract class Produit {
    private int idnum;
    private  String nom;
    private double prix;

    private float quantité;


    public String getNom() {
        return nom;
    }

    public Produit(int idnum, String nom, double prix, float quantité) {
        this.idnum = idnum;
        this.nom = nom;
        this.prix = prix;
        this.quantité = quantité;
    }

    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int idnum) {
        this.idnum = idnum;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }



    public float getQuantité() {
        return quantité;
    }

    public void setQuantité(float quantité) {
        this.quantité = quantité;
    }
    public  abstract void consulter ();

}
