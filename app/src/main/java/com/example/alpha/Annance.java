package com.example.alpha;

public class Annance extends Produit {
    private int idnum;
    //image
    private  String description ;

    public Annance(int idnum, String nom, double prix, float quantité, int idnum1, String description) {
        super(idnum, nom, prix, quantité);
        this.idnum = idnum1;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void consulter() {

    }
}
