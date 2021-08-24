package data_access.interfaces;

import java.util.List;

public interface GeneriqueDAO<ENTITY,Integer> {

    //READ
    ENTITY getById(Integer idMarque);
    List<ENTITY> getAll();

    //CREATE
    boolean insert(ENTITY marque);

    //UPDATE
    boolean update(ENTITY marque);

    //DELETE
    boolean delete(Integer idMarque);

}
