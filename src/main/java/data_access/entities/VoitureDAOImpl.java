package data_access.entities;

import Classes.Modele;
import Classes.Option;
import Classes.Voiture;
import Classes.Voiture_Option;
import com.mysql.cj.protocol.x.ResultMessageListener;
import data_access.interfaces.VoitureDAO;
import data_access.interfaces.Voiture_OptionDAO;
import jdbc.MySqlConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAOImpl implements VoitureDAO {

    private List<Option> getAllOptions(int idVoiture) throws SQLException {
        List<Option> optionList = new ArrayList<>();
        List<Voiture_Option> voiture_optionList = new Voiture_OptionDAOImpl().getAll();

        for (Voiture_Option voiture_option : voiture_optionList) {
            if (voiture_option.getVoiture().getId_voiture() == idVoiture)
                optionList.add(voiture_option.getOption());
        }

        return optionList;
    }

    private List<Voiture_Option> getAllVoitureOptions(ResultSet res) throws SQLException {
        List<Voiture_Option> listVoitOpt = new ArrayList<>();

        while (res.next()){
            listVoitOpt.add(new Voiture_OptionDAOImpl().convertToObject(res));
        }

        return listVoitOpt;
    }

    public Voiture convertToObject(ResultSet res) throws SQLException {

        int id = res.getInt("id_voiture");
        Modele modele = new ModeleDAOImpl().getById(res.getInt("modele_id"));
        double prix = res.getDouble("prix");
        String couleur = res.getString("couleur");
        String carburant = res.getString("carburant");
        double kilometre = res.getDouble("kilometre");

        Voiture voiture = new Voiture(id, modele, getAllOptions(id), prix, couleur, carburant, kilometre);
        return voiture;

    }

    public Voiture convertToObjectWithoutOptions(ResultSet res) throws SQLException {
        int id = res.getInt("id_voiture");
        Modele modele = new ModeleDAOImpl().getById(res.getInt("modele_id"));
        double prix = res.getDouble("prix");
        String couleur = res.getString("couleur");
        String carburant = res.getString("carburant");
        double kilometre = res.getDouble("kilometre");

        Voiture voiture = new Voiture(id, modele, null, prix, couleur, carburant, kilometre);
        return voiture;

    }


    public Voiture getByIdWithoutOptionsRef(Integer idVoiture) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement voiturePrepStat = connection.prepareStatement("SELECT * FROM Voiture WHERE id_voiture = ?");
        ){
            voiturePrepStat.setInt(1, idVoiture);

            try(ResultSet rsV = voiturePrepStat.executeQuery()){

                if (rsV.next())
                    return convertToObjectWithoutOptions(rsV);
                else
                    return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors du VoitureDAOImpl getById()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Voiture getById(Integer idVoiture) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM Voiture WHERE id_voiture = ?");

             //Statement myStatement = connection.createStatement();
             //ResultSet rs = myStatement.executeQuery("SELECT * FROM Option WHERE id_option = "+id_option);
        ){
            prepareStatement.setInt(1, idVoiture);

            try(ResultSet rs = prepareStatement.executeQuery();){
                if (rs.next())
                    return convertToObject(rs);
                else
                    return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors du VoitureDAO getById()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Voiture> getAll() {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM Voiture");
             ResultSet rs = prepStat.executeQuery()
        ){

            List<Voiture> listRes = new ArrayList<>();

            while (rs.next())
                listRes.add(convertToObject(rs));

            if (listRes.size() == 0)
                return null;
            else
                return listRes;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du VoitureDAO getAll()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Voiture v) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO Voiture Values(?,?,?,?,?,?)");
             PreparedStatement voprepStat = connection.prepareStatement("SELECT * FROM Voiture_Option");
        ){

            prepareStatement.setInt(1, v.getId_voiture());
            prepareStatement.setInt(2, v.getModele().getId_modele());
            prepareStatement.setDouble(3, v.getPrix());
            prepareStatement.setString(4, v.getCouleur());
            prepareStatement.setString(5, v.getCarburant());
            prepareStatement.setDouble(6, v.getKilometre());

            int ret = prepareStatement.executeUpdate();
            ResultSet res = voprepStat.executeQuery();

            if (ret > 0){
                List<Voiture_Option> listVO = getAllVoitureOptions(res);
                Voiture_OptionDAOImpl voDAO = new Voiture_OptionDAOImpl();
                for (Voiture_Option vo : listVO)
                    voDAO.insert(vo);
//                while (res.next()){
//                    Voiture_OptionDAOImpl voDAO = new Voiture_OptionDAOImpl();
//                    Voiture_Option vo = voDAO.convertToObject(res);
//                    voDAO.insert(vo);
//                }
            }

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du VoitureDAOImpl insert()");
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Voiture v) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("UPDATE Voiture SET modele_id=?, prix=?, couleur=?, carburant=?, kilometre=? WHERE id_voiture=?");
             PreparedStatement voprepStat = connection.prepareStatement("SELECT * FROM Voiture_Option");
             //Statement myStatement = connection.createStatement();
        ){
            //int ret = myStatement.executeUpdate("UPDATE technobel.Option SET NOM='"+o.getNom()+"', prix="+o.getPrix()+" WHERE id_option="+o.getId_option());
            prepStat.setInt(1, v.getModele().getId_modele());
            prepStat.setDouble(2, v.getPrix());
            prepStat.setString(3, v.getCouleur());
            prepStat.setString(4, v.getCarburant());
            prepStat.setDouble(5, v.getKilometre());
            prepStat.setInt(6, v.getId_voiture());

            int ret = prepStat.executeUpdate();
            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du VoitureDAO update()");
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Integer idVoiture) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("DELETE FROM Voiture WHERE id_voiture=?")
             //Statement myStatement = connection.createStatement();
        ){
            prepStat.setInt(1, idVoiture);
            int ret = prepStat.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du VoitureDAOImpl delete()");
            ex.printStackTrace();
        }

        return false;
    }
}
