package data_access.entities;

import Classes.Marque;
import Classes.Modele;
import Classes.Option;
import data_access.interfaces.ModeleDAO;
import jdbc.MySqlConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeleDAOImpl implements ModeleDAO {

    private Modele convertToObject(ResultSet res) throws SQLException {

        int id = res.getInt("id_modele");
        String nom = res.getString("nom");
        int idMarque = res.getInt("marque_id");
        Marque marque = new MarqueDAOImpl().getById(idMarque);
        Modele modele = new Modele(id, nom, marque);
        return modele;

    }

    @Override
    public Modele getById(Integer idModele) {

        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM modele WHERE id_modele = ?");
        ){
            prepareStatement.setInt(1, idModele);

            try(ResultSet rs = prepareStatement.executeQuery();){
                if (rs.next())
                    return convertToObject(rs);
                else
                    return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors du ModeleDAO getById()");
            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public List<Modele> getAll() {

        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM modele");
             ResultSet rs = prepStat.executeQuery()
        ){

            List<Modele> listRes = new ArrayList<>();

            while (rs.next())
                listRes.add(convertToObject(rs));

            if (listRes.size() == 0)
                return null;
            else
                return listRes;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du ModeleDAO getAll()");
            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public boolean insert(Modele m) {

        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO modele Values(?,?,?)")
        ){

            prepareStatement.setInt(1, m.getId_modele());
            prepareStatement.setString(2, m.getNom());
            prepareStatement.setInt(3, m.getMarque().getId_marque());

            int ret = prepareStatement.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du ModeleDAO insert()");
            ex.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean update(Modele m) {

        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("UPDATE modele SET nom=?, marque_id=? WHERE id_modele=?");
        ){
            prepStat.setString(1, m.getNom());
            prepStat.setDouble(2, m.getId_modele());
            prepStat.setInt(3, m.getId_modele());

            int ret = prepStat.executeUpdate();
            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du ModeleDAO update()");
            ex.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean delete(Integer idModele) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("DELETE FROM modele WHERE id_modele=?")
        ){
            prepStat.setInt(1, idModele);
            int ret = prepStat.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du ModeleDAO delete()");
            ex.printStackTrace();
        }

        return false;
    }
}
