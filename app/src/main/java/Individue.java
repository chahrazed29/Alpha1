public class Individue extends Utilisateur{


    private String nom;
    private String prenom;

    public Individue(int idnum, String nomUtilisateur, int telephone, String email, String mdp, String nom, String prenom) {
        super(idnum, nomUtilisateur, telephone, email, mdp);
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
