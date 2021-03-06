package Classes;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voiture {
    private int id_voiture;
    private Modele modele;
    private List<Option> listOptions;
    private double prix;
    private String couleur;
    private String carburant;
    private double kilometre;

    public Voiture(int id_voiture, Modele modele, List<Option> listOptions, double prix, String couleur, String carburant, double kilometre) {
        this.id_voiture = id_voiture;
        this.modele = modele;
        this.listOptions = listOptions;
        this.prix = prix;
        this.couleur = couleur;
        this.carburant = carburant;
        this.kilometre = kilometre;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public List<Option> getListOptions() {
        return listOptions;
    }

    public void setListOptions(List<Option> listOptions) {
        this.listOptions = listOptions;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public double getKilometre() {
        return kilometre;
    }

    public void setKilometre(double kilometre) {
        this.kilometre = kilometre;
    }

    public List<Voiture_Option> genererListVoitureOptions(List<Voiture_Option> listFromDB) {
        List<Voiture_Option> listGen = new ArrayList<>();

        int firstID;

        if(listFromDB.size() == 0)
            firstID = 1;
        else
            firstID = listFromDB.get(listFromDB.size()-1).getId_voiture_option()+1;

        for (Option opt : listOptions) {
            listGen.add(new Voiture_Option(firstID, this,opt));
            firstID++;
        }

        return listGen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture voiture = (Voiture) o;
        return id_voiture == voiture.id_voiture && Double.compare(voiture.prix, prix) == 0 && Double.compare(voiture.kilometre, kilometre) == 0 && modele.equals(voiture.modele) && Objects.equals(listOptions, voiture.listOptions) && couleur.equals(voiture.couleur) && carburant.equals(voiture.carburant);
    }

    @Override
    public String toString() {
        String optionString;

        if (listOptions.size() == 0)
            optionString = "-";
        else
            optionString = "\n";

        for (int i = 0; i < listOptions.size(); i++) {
            if (i == listOptions.size() -1)
                optionString = optionString.concat(listOptions.get(i).toString());
            else
                optionString = optionString.concat(listOptions.get(i).toString() + "\n");
        }

        return "\nVoiture #"  + id_voiture +
                "\nMod??le: " + modele.getNom() + " ("+modele.getMarque().getNom()+")"+
                "\nOption(s): " + optionString +
                "\nPrix: " + prix + " eur" +
                "\nCouleur: '" + couleur + '\'' +
                "\narburant: '" + carburant + '\'' +
                "\nKm: " + kilometre+"\n";
    }
}
