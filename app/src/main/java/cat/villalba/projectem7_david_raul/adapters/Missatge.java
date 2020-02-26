package cat.villalba.projectem7_david_raul.adapters;

public class Missatge {

    private String remitent;
    private String receptor;
    private String missatge;

    public Missatge(String remitent, String receptor, String missatge) {
        this.remitent = remitent;
        this.receptor = receptor;
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

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }
}
