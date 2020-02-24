package cat.villalba.projectem7_david_raul.activities;

public class Contacte {

    private String id;
    private String imageURL;

    public Contacte(String id, String imageURL) {
        this.id = id;
        this.imageURL = imageURL;
    }

    public Contacte() {}

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
