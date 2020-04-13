package com.example.alpha;

public class Individue extends Utilisateur {


    public Individue(int idnum, String username, int phone, String email, String password, String desc, int idlocation, String wilaya) {
        super(idnum, username, phone, email, password, desc, idlocation, wilaya);
    }

    public Individue(int idnum, String username, int phone, String email, String desc, int idlocation,String wilaya) {
        super(idnum, username, phone, email, desc, idlocation,wilaya);
    }
    public Individue(){

    }
}
