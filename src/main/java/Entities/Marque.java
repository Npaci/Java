package Entities;

import java.util.Objects;

public class Marque {
    private int id_marque;
    private String nom;

    public Marque(int id_marque, String nom) {
        this.id_marque = id_marque;
        this.nom = nom;
    }

    public int getId_marque() {
        return id_marque;
    }

    public void setId_marque(int id_marque) {
        this.id_marque = id_marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marque marque = (Marque) o;
        return id_marque == marque.id_marque && nom.equals(marque.nom);
    }

    @Override
    public String toString() {
        return "Marque [ID: " + id_marque +
                ", nom: '" + nom + '\'' + "]";
    }
}
