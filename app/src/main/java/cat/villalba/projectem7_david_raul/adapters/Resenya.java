package cat.villalba.projectem7_david_raul.adapters;

public class Resenya {

    private String resenyaId;
    private String usuariId;
    private String peliculaId;
    private String textResenya;
    private long notaDiversitat;
    private long notaLgti;
    private long notaConsciencia;
    private long notaCultural;


    public Resenya() {
    }

    public Resenya(String resenyaId, String usuariId, String peliculaId, String textResenya, long notaDiversitat, long notaLgti, long notaConsciencia, long notaCultural) {
        this.resenyaId = resenyaId;
        this.usuariId = usuariId;
        this.peliculaId = peliculaId;
        this.textResenya = textResenya;
        this.notaDiversitat = notaDiversitat;
        this.notaLgti = notaLgti;
        this.notaConsciencia = notaConsciencia;
        this.notaCultural = notaCultural;
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

    public long getNotaDiversitat() {
        return notaDiversitat;
    }

    public void setNotaDiversitat(long notaDiversitat) {
        this.notaDiversitat = notaDiversitat;
    }

    public long getNotaLgti() {
        return notaLgti;
    }

    public void setNotaLgti(long notaLgti) {
        this.notaLgti = notaLgti;
    }

    public long getNotaConsciencia() {
        return notaConsciencia;
    }

    public void setNotaConsciencia(long notaConsciencia) {
        this.notaConsciencia = notaConsciencia;
    }

    public long getNotaCultural() {
        return notaCultural;
    }

    public void setNotaCultural(long notaCultural) {
        this.notaCultural = notaCultural;
    }
}



