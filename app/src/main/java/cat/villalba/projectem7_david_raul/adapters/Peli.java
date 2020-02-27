package cat.villalba.projectem7_david_raul.adapters;

public class Peli {

    private String titulo;
    private String anyo;
    private String sinopsis;
    private String tematica;
    private long notaDiversitat;
    private long notaLgtbi;
    private long notaConsciencia;
    private long notaCultural;
    private String imageResource;

    public Peli(String titulo, String anyo, String sinopsis, String tematica, String imageResource) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.sinopsis = sinopsis;
        this.imageResource = imageResource;
        this.tematica = tematica;
    }

    public Peli() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String año) {
        this.anyo = año;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public long getNotaDiversitat() {
        return notaDiversitat;
    }

    public void setNotaDiversitat(long notaDiversitat) {
        this.notaDiversitat = notaDiversitat;
    }

    public long getNotaLgtbi() {
        return notaLgtbi;
    }

    public void setNotaLgti(long notaLgti) {
        this.notaLgtbi = notaLgti;
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

    public String getImageResource() {
        return imageResource;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}
