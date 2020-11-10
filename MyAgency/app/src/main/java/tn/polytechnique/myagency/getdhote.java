package tn.polytechnique.myagency;



public class getdhote {
    private int iddhote;
    private double prix;
    private String nom;
    private String kilometrage;
    private String emplacement;
    private String image;
    private String lati;
    private String longi;

    public getdhote() {
        this.iddhote = iddhote;
        this.prix = prix;
        this.nom = nom;
        this.kilometrage = kilometrage;
        this.emplacement = emplacement;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public int getIddhote() {
        return iddhote;
    }

    public double getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public String getKilometrage() {
        return kilometrage;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setIddhote(int iddhote) {
        this.iddhote = iddhote;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
}

