package cat.villalba.projectem7_david_raul;

public class Peli {

    private String titulo;
    private String info;
    private String resumen;
    private String director;
    private final int imageResource;

    Peli(String titulo, String info, int imageResource, String resumen, String director) {
        this.titulo = titulo;
        this.info = info;
        this.imageResource = imageResource;
        this.director = director;
        this.resumen = resumen;
    }

    String getTitulo() {return titulo;}
    String getInfo() { return info; }
    String getResumen() {return resumen;}
    String getDirector() {return director;}
    int getImageResource() { return imageResource;}
}
