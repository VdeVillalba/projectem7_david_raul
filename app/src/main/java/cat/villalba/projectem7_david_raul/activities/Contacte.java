package cat.villalba.projectem7_david_raul.activities;

public class Contacte {

    private String id;
    private String imageURL;
    private String nomContacte;

    public Contacte(String id, String nomContacte, String imageURL) {
        this.id = id;
        this.imageURL = imageURL;
        this.nomContacte = nomContacte;
    }

    public Contacte() {}

    public String getNomContacte() {
        return nomContacte;
    }

    public void setNomContacte(String nomContacte) {
        this.nomContacte = nomContacte;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
