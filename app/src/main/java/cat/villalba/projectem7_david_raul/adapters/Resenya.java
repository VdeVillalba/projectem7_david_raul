package cat.villalba.projectem7_david_raul.adapters;

import java.util.Calendar;

public class Resenya {

    private String resenyaId;
    private String usuariId;
    private String peliculaId;
    private String textResenya;
    private int nota;

    public Resenya() {}

    public Resenya(String resenyaId, String usuariId, String peliculaId, String textResenya, int nota) {
        this.resenyaId = resenyaId;
        this.usuariId = usuariId;
        this.peliculaId = peliculaId;
        this.textResenya = textResenya;
        this.nota = nota;
    }

    public String getResenyaId() {
        return resenyaId;
    }

    public void setResenyaId(String resenyaId) {
        this.resenyaId = resenyaId;
    }

    public String getUsuariId() {
        return usuariId;
    }

    public void setUsuariId(String usuariId) {
        this.usuariId = usuariId;
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(String peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getTextResenya() {
        return textResenya;
    }

    public void setTextResenya(String textResenya) {
        this.textResenya = textResenya;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}


