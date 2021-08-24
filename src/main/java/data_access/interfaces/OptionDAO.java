package data_access.interfaces;

import Classes.Option;

import java.util.List;

public interface OptionDAO extends GeneriqueDAO<Option,Integer> {

    //READ
    Option getById(Integer idMarque);
    List<Option> getAll();

    //CREATE
    boolean insert(Option option);

    //UPDATE
    boolean update(Option option);

    //DELETE
    boolean delete(Integer idOption);

}
