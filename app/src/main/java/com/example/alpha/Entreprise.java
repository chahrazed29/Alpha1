package com.example.alpha;

public class Entreprise extends Utilisateur {

        private int numrc;


    public Entreprise(int idnum, String username, int phone, String email, String password, String desc, int idlocation, int numrc,String wilaya) {
        super(idnum, username, phone, email, password, desc, idlocation,wilaya);
        this.numrc = numrc;
    }
    public Entreprise(int idnum, String username, int phone, String email, String desc, int idlocation, int numrc,String wilaya) {
        super(idnum, username, phone, email,desc, idlocation,wilaya);
        this.numrc = numrc;
    }
    public Entreprise(){

    }

    public int getNumrc() {
        return numrc;
    }

    public void setNumrc(int numrc) {
        this.numrc = numrc;
    }
}
