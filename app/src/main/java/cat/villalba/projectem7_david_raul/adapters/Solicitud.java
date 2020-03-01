package cat.villalba.projectem7_david_raul.adapters;

public class Solicitud {

    private String codiPeticio;
    private String emisor;
    private String receptor;

    public Solicitud() {}

    public Solicitud(String codiPeticio, String emisor, String receptor) {
        this.codiPeticio = codiPeticio;
        this.emisor = emisor;
        this.receptor = receptor;
    }

    public String getCodiPeticio() {
        return codiPeticio;
    }

    public void setCodiPeticio(String codiPeticio) {
        this.codiPeticio = codiPeticio;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
}
