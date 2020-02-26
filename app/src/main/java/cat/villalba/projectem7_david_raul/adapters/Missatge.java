package cat.villalba.projectem7_david_raul.adapters;

public class Missatge {

    private String remitent;
    private String destinatari;
    private String missatge;

    public Missatge(String remitent, String destinatari, String missatge) {
        this.remitent = remitent;
        this.destinatari = destinatari;
        this.missatge = missatge;
    }

    public Missatge() {
    }

    public String getRemitent() {
        return remitent;
    }

    public void setRemitent(String remitent) {
        this.remitent = remitent;
    }

    public String getDestinatari() {
        return destinatari;
    }

    public void setDestinatari(String receptor) {
        this.destinatari = receptor;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }
}
