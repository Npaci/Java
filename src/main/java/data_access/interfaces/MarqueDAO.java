package data_access.interfaces;

import Classes.Marque;

import java.util.List;

public interface MarqueDAO extends GeneriqueDAO<Marque, Integer>{

    //READ
    Marque getById(Integer idMarque);
    List<Marque> getAll();

    //CREATE
    boolean insert(Marque marque);

    //UPDATE
    boolean update(Marque marque);

    //DELETE
    boolean delete(Integer idMarque);

}
