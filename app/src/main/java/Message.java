import java.util.Date;

public class Message {
    private  int idmsg;
    private String contenu ;
    private Date date_heure;

    public Message(int idmsg, String contenu, Date date_heure) {
        this.idmsg = idmsg;
        this.contenu = contenu;
        this.date_heure = date_heure;
    }

    public int getIdmsg() {
        return idmsg;
    }

    public void setIdmsg(int idmsg) {
        this.idmsg = idmsg;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }
    public void envoyer ()
    {};
    public void supprimer ()
    {};
    public void recevoir ()
    {};
}
