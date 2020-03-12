package com.example.alpha;

import java.util.Date;

public class Notification {
    private  int idnotf;
    private Date date_heure ;

    public Notification(int idnotf, Date date_heure) {
        this.idnotf = idnotf;
        this.date_heure = date_heure;
    }

    public int getIdnotf() {
        return idnotf;
    }

    public void setIdnotf(int idnotf) {
        this.idnotf = idnotf;
    }

    public Date getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }
    public  void supprimer ()
    {};
    public  void envoyer  ()
    {};
}
