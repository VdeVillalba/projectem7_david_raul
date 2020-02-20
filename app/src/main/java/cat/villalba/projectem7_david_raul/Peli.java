package cat.villalba.projectem7_david_raul;

public class Peli {

    private String titulo;
    private String director;
    private final int imageResource;

    public Peli(String titulo, int imageResource, String director) {
        this.titulo = titulo;
        this.imageResource = imageResource;
        this.director = director;
    }

    String getTitulo() {return titulo;}
    String getDirector() {return director;}
    int getImageResource() { return imageResource;}
}
