package com.example.alpha;

public class Utilisateur {
    private long idnum;
    private String username;
    private long phone;
    private String email;
    private String password;
    //localisation


    public Utilisateur( String username, long phone, String email, String password) {

        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(){

    }

    public long getIdnum() {
        return idnum;
    }

    public void setIdnum(long idnum) {
        this.idnum = idnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username= username;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
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
