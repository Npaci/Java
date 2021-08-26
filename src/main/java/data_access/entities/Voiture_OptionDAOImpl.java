package data_access.entities;

import Classes.Modele;
import Classes.Option;
import Classes.Voiture;
import Classes.Voiture_Option;
import data_access.interfaces.Voiture_OptionDAO;
import jdbc.MySqlConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Voiture_OptionDAOImpl implements Voiture_OptionDAO {
    //Si add est vrai on va retourner les tuples Voiture_Option à ajouter dans la BD
    //Si add est faux on retournera les tuples Voiture_Option correspondant à supprimer
    public List<Voiture_Option> genererVoitureOptions(Voiture v, List<Option> optList, boolean add) {
        List<Voiture_Option> listGen = new ArrayList<>();

        if (v != null && optList != null) {
            List<Voiture_Option> listFromDB = new Voiture_OptionDAOImpl().getAll();

            if (add) {
                int firstID;

                if (listFromDB.size() == 0)
                    firstID = 1;
                else
                    firstID = listFromDB.get(listFromDB.size() - 1).getId_voiture_option() + 1;

                for (Option opt : optList) {
                    listGen.add(new Voiture_Option(firstID, v, opt));
                    firstID++;
                }
            } else {
                for (Option opt : optList) {
                    for (Voiture_Option vo : listFromDB) {
                        if (v.getId_voiture() == vo.getVoiture().getId_voiture() &&
                                opt.getId_option() == vo.getOption().getId_option())
                            listGen.add(new Voiture_Option(vo.getId_voiture_option(), v, opt));
                    }
                }
            }
        }

        return listGen;
    }

    public Voiture_Option convertToObject(ResultSet res) throws SQLException {

        int id_voiture_option = res.getInt("id_voiture_option");
        int voiture_id = res.getInt("voiture_id");
        Voiture voiture = new VoitureDAOImpl().getByIdWithoutOptionsRef(voiture_id);
        int modele_id = res.getInt("option_id");
        Option option = new OptionDAOIpml().getById(modele_id);

        Voiture_Option voiture_option = new Voiture_Option(id_voiture_option, voiture, option);
        return voiture_option;

    }

    @Override
    public Voiture_Option getById(Integer idVO) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM Voiture_Option WHERE id_voiture_option = ?");
        ){
            prepareStatement.setInt(1, idVO);

            try(ResultSet rs = prepareStatement.executeQuery();){
                if (rs.next())
                    return convertToObject(rs);
                else
                    return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors du Voiture_OptionDAOImpl getById()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Voiture_Option> getAll() {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM Voiture_Option");
             ResultSet rs = prepStat.executeQuery()
        ){

            List<Voiture_Option> listRes = new ArrayList<>();

            while (rs.next())
                listRes.add(convertToObject(rs));

            if (listRes.size() == 0)
                return null;
            else
                return listRes;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du Voiture_OptionDAOImpl getAll()");
            ex.printStackTrace();
        }

        return null;
    }

    //Vérifie si une paire voiture_option n'existe pas déjà
    private boolean checkDoublon(Voiture_Option vo) {
        List<Voiture_Option> voiture_optionList = new Voiture_OptionDAOImpl().getAll();

        for (Voiture_Option voiture_option : voiture_optionList) {
            if (voiture_option.getVoiture().getId_voiture() == vo.getVoiture().getId_voiture()
                    && voiture_option.getOption().getId_option() == vo.getOption().getId_option()){
                System.out.println("La voiture possède #"+vo.getVoiture().getId_voiture()+
                        " possède déjà l'option #"+vo.getOption().getId_option());
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean insert(Voiture_Option vo) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO Voiture_Option Values(?,?,?)")
        ){
            int ret = 0;

            if(!checkDoublon(vo)) { // La paire voiture_option ne sera ajouter que si on ne trouve pas de doublon et non sur la base de l'ID
                prepareStatement.setInt(1, vo.getId_voiture_option());
                prepareStatement.setInt(2, vo.getVoiture().getId_voiture());
                prepareStatement.setDouble(3, vo.getOption().getId_option());

                ret = prepareStatement.executeUpdate();
            }

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du Voiture_OptionDAOImpl insert()");
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Voiture_Option vo) {
        //Modification de tuples de cette table n'est pas autorisée (uniquement INSERT et DELETE)
        return false;
    }

    @Override
    public boolean delete(Integer idVO) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("DELETE FROM Voiture_Option WHERE id_voiture_option = ?")
             //Statement myStatement = connection.createStatement();
        ){
            prepStat.setInt(1, idVO);
            int ret = prepStat.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du Voiture_OptionDAOImpl delete()");
            ex.printStackTrace();
        }

        return false;
    }
}
