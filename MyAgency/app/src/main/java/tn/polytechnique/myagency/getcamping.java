package tn.polytechnique.myagency;



public class getcamping{
    private int idComping;
    private double prix;
    private String date;
    private String destination;
    private String description;
    private String image;

    public getcamping() {
        this.idComping = idComping;
        this.prix = prix;
        this.date = date;
        this.destination = destination;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdComping() {
        return idComping;
    }

    public double getPrix() {
        return prix;
    }

    public String getDate() {
        return date;
    }

    public String getDestination() {
        return destination;
    }

    public String getDescription() {
        return description;
    }

    public void setIdComping(int idComping) {
        this.idComping = idComping;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
