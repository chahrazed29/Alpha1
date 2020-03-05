public class Enterprise extends Utilisateur{


    private int numrc;


    public Enterprise(int idnum, String nomUtilisateur, int telephone, String email, String mdp, int numrc) {
        super(idnum, nomUtilisateur, telephone, email, mdp);
        this.numrc = numrc;
    }

    public int getNumrc() {
        return numrc;
    }

    public void setNumrc(int numrc) {
        this.numrc = numrc;
    }
}
