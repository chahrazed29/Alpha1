package com.example.alpha;

public class Utilisateur {
    private int idnum;
    private String username;
    private int phone;
    private String email;
    private String password;
    private String desc;
    private int idlocation;
   private String wilaya;

    public Utilisateur(int idnum, String username, int phone, String email, String password, String desc, int idlocation,String wilaya) {
        this.idnum = idnum;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.desc = desc;
        this.idlocation = idlocation;
        this.wilaya = wilaya;
    }
    public Utilisateur(int idnum, String username, int phone, String email, String desc, int idlocation,String wilaya) {
        this.idnum = idnum;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.desc = desc;
        this.idlocation = idlocation;
        this.wilaya = wilaya;
    }
    public Utilisateur(){

    }

    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int idnum) {
        this.idnum = idnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(int idlocation) {
        this.idlocation = idlocation;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void  connecter ()
    {};
    public void  deconnecter ()
    {};
    public void recherche()
    {};
}
