package data_access.entities;

import Classes.Marque;
import Classes.Option;
import data_access.interfaces.OptionDAO;
import jdbc.MySqlConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OptionDAOIpml implements OptionDAO {

    private Option convertToObject(ResultSet res) throws SQLException {

        int id = res.getInt("id_option");
        String nom = res.getString("nom");
        double prix = res.getDouble("prix");
        Option option = new Option(id, nom, prix);
        return option;

    }

    @Override
    public Option getById(Integer idOption) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM technobel.Option WHERE id_option = ?");

             //Statement myStatement = connection.createStatement();
             //ResultSet rs = myStatement.executeQuery("SELECT * FROM Option WHERE id_option = "+id_option);
        ){
            prepareStatement.setInt(1, idOption);

            try(ResultSet rs = prepareStatement.executeQuery();){
                if (rs.next())
                    return convertToObject(rs);
                else
                    return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors du OptionDAO getById()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Option> getAll() {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM technobel.Option");
             ResultSet rs = prepStat.executeQuery()
//             Statement myStatement = connection.createStatement();
//             ResultSet rs = myStatement.executeQuery("SELECT * FROM OPTION");
        ){

            List<Option> listRes = new ArrayList<>();

            while (rs.next())
                listRes.add(convertToObject(rs));

            if (listRes.size() == 0)
                return null;
            else
                return listRes;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du OptionDAO getAll()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(Option o) {

        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO technobel.Option Values(?,?,?)")
        ){

            prepareStatement.setInt(1, o.getId_option());
            prepareStatement.setString(2, o.getNom());
            prepareStatement.setDouble(3, o.getPrix());

            int ret = prepareStatement.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du OptionDAO insert()");
            ex.printStackTrace();
        }

        return false;

    }

    @Override
    public boolean update(Option o) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("UPDATE technobel.Option SET NOM=?, prix=? WHERE id_option=?");
             //Statement myStatement = connection.createStatement();
        ){
            //int ret = myStatement.executeUpdate("UPDATE technobel.Option SET NOM='"+o.getNom()+"', prix="+o.getPrix()+" WHERE id_option="+o.getId_option());
            prepStat.setString(1, o.getNom());
            prepStat.setDouble(2, o.getPrix());
            prepStat.setInt(3, o.getId_option());

            int ret = prepStat.executeUpdate();
            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du OptionDAO update()");
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Integer idOption) {

        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("DELETE FROM technobel.option WHERE id_option=?")
             //Statement myStatement = connection.createStatement();
        ){
            prepStat.setInt(1, idOption);
            int ret = prepStat.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du OptionDAO delete()");
            ex.printStackTrace();
        }

        return false;

    }
}
