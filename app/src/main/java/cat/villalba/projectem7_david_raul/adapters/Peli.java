package cat.villalba.projectem7_david_raul.adapters;

public class Peli {

    private String titulo;
    private String anyo;
    private String sinopsis;
    private String imageResource;


    public Peli(String titulo, String anyo, String sinopsis, String imageResource) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.sinopsis = sinopsis;
        this.imageResource = imageResource;
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

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}
