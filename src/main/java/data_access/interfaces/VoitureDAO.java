package data_access.interfaces;

import Classes.Option;
import Classes.Voiture;

import java.util.List;

public interface VoitureDAO extends GeneriqueDAO<Voiture, Integer> {

    //READ
    Voiture getById(Integer idVoiture);
    List<Voiture> getAll();

    //CREATE
    boolean insert(Voiture voiture);

    //UPDATE
    boolean update(Voiture voiture);

    //DELETE
    boolean delete(Integer idVoiture);

}
