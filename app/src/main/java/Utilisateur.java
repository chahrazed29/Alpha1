public class Utilisateur {
    private int idnum;
    private String nomUtilisateur;
    private int telephone;
    private String email;
    private String mdp;
    //localisation


    public Utilisateur(int idnum, String nomUtilisateur, int telephone, String email, String mdp) {
        this.idnum = idnum;
        this.nomUtilisateur = nomUtilisateur;
        this.telephone = telephone;
        this.email = email;
        this.mdp = mdp;
    }

    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int idnum) {
        this.idnum = idnum;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void  connecter ()
    {};
    public void  deconnecter ()
    {};
    public void recherche()
    {};
}
