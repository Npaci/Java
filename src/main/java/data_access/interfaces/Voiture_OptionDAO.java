package data_access.interfaces;

import Classes.Modele;
import Classes.Voiture_Option;

import java.util.List;

public interface Voiture_OptionDAO extends GeneriqueDAO<Voiture_Option, Integer> {

    //READ
    Voiture_Option getById(Integer idVO);
    List<Voiture_Option> getAll();

    //CREATE
    boolean insert(Voiture_Option vo);

    //UPDATE
    boolean update(Voiture_Option vo);

    //DELETE
    boolean delete(Integer idVO);

}
