package data_access.interfaces;

import Classes.Modele;
import Classes.Option;

import java.util.List;

public interface ModeleDAO extends GeneriqueDAO<Modele, Integer>{

    //READ
    Modele getById(Integer idModele);
    List<Modele> getAll();

    //CREATE
    boolean insert(Modele m);

    //UPDATE
    boolean update(Modele m);

    //DELETE
    boolean delete(Integer idModele);

}
