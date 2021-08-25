package Classes;

import java.util.Objects;

public class Voiture_Option {
    private int id_voiture_option;
    private Voiture voiture;
    private Option option;

    public Voiture_Option(int id_voiture_option, Voiture voiture, Option option) {
        this.id_voiture_option = id_voiture_option;
        this.voiture = voiture;
        this.option = option;
    }

    public int getId_voiture_option() {
        return id_voiture_option;
    }

    public void setId_voiture_option(int id_voiture_option) {
        this.id_voiture_option = id_voiture_option;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture_Option that = (Voiture_Option) o;
        return voiture.equals(that.voiture) && option.equals(option);
    }

    @Override
    public String toString() {
        return "Voiture_Option [" +
                "voiture= #" + voiture.getId_voiture() +
                ", option= #" + option.getId_option() +
                ']';
    }
}
