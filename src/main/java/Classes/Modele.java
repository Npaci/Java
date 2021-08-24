package Classes;

public class Modele {
    private int id_modele;
    private String nom;
    private Marque marque;

    public Modele(int id_modele, String nom, Marque marque) {
        this.id_modele = id_modele;
        this.nom = nom;
        this.marque = marque;
    }

    public int getId_modele() {
        return id_modele;
    }

    public void setId_modele(int id_modele) {
        this.id_modele = id_modele;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modele modele = (Modele) o;
        return id_modele == modele.id_modele && nom.equals(modele.nom) && marque.equals(modele.marque);
    }

    @Override
    public String toString() {
        return "Modele{" +
                "id_modele=" + id_modele +
                ", nom='" + nom + '\'' +
                ", marque=" + marque.getNom() +
                '}';
    }
}
