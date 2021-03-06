package cat.villalba.projectem7_david_raul.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Contacte {

    private String id;
    private String imageURL;
    private String nomContacte;
    private Map<String,String> amics;
    private List<String> interessos;

    public Contacte(String id, String nomContacte, String imageURL, Map<String,String> amics, ArrayList<String> interessos) {
        this.id = id;
        this.imageURL = imageURL;
        this.nomContacte = nomContacte;
        this.amics = amics;
        this.interessos = interessos;
    }

    public Contacte() {
    }

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

    public Map<String,String> getAmics() {
        return amics;
    }

    public void setAmics(Map<String,String> amics) {
        this.amics = amics;
    }

    public List<String> getInteressos() {
        return interessos;
    }

    public void setInteressos(List<String> interessos) {
        this.interessos = interessos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacte contacte = (Contacte) o;
        return id.equals(contacte.id) &&
                nomContacte.equals(contacte.nomContacte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomContacte);
    }
}
