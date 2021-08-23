package Entities;

import java.util.Objects;

public class Option {
    private int id_option;
    private String nom;
    private double prix;

    public Option(int id_option, String nom, double prix) {
        this.id_option = id_option;
        this.nom = nom;
        this.prix = prix;
    }

    public int getId_option() {
        return id_option;
    }

    public void setId_option(int id_option) {
        this.id_option = id_option;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id_option == option.id_option && Double.compare(option.prix, prix) == 0 && nom.equals(option.nom);
    }

    @Override
    public String toString() {
        return "Opt [Id: " + id_option +
                ", nom: '" + nom + '\'' +
                ", prix: " + prix +" eur]";
    }
}
