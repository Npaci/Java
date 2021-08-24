package data_access.entities;

import Classes.Marque;
import data_access.interfaces.MarqueDAO;
import jdbc.MySqlConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarqueDAOImpl implements MarqueDAO {

    private Marque convertToObject(ResultSet res) throws SQLException {

        int id = res.getInt("id_marque");
        String nom = res.getString("nom");
        Marque marque = new Marque(id, nom);
        return marque;

    }

    @Override
    public Marque getById(Integer idMarque) {
        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM MARQUE WHERE id_marque = ?");

             //Statement myStatement = connection.createStatement();
             //ResultSet rs = myStatement.executeQuery("SELECT * FROM MARQUE WHERE id_marque = "+idMarque);
        ){
            prepareStatement.setInt(1, idMarque);

            try(ResultSet rs = prepareStatement.executeQuery();){
                if (rs.next())
                    return convertToObject(rs);
                else
                    return null;
            }

        } catch (SQLException ex) {
            System.out.println("Erreur lors du MarqueDAO getById()");
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Marque> getAll() {

        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM MARQUE");
             ResultSet rs = prepStat.executeQuery()
//             Statement myStatement = connection.createStatement();
//             ResultSet rs = myStatement.executeQuery("SELECT * FROM MARQUE");
        ){

            List<Marque> listRes = new ArrayList<>();

            while (rs.next())
                listRes.add(convertToObject(rs));

            if (listRes.size() == 0)
                return null;
            else
                return listRes;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du MarqueDAO getAll()");
            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public boolean insert(Marque m) {

        try (// Conn, Stmnt et Resultset sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO MARQUE Values(?,?)");
             //Statement myStatement = connection.createStatement();
        ){
            //int ret = myStatement.executeUpdate("INSERT INTO MARQUE Values("+m.getId_marque()+",'"+m.getNom()+"')");
            prepareStatement.setInt(1, m.getId_marque());
            prepareStatement.setString(2, m.getNom());

            int ret = prepareStatement.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du MarqueDAO insert()");
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(Marque m) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("UPDATE MARQUE SET NOM=? WHERE id_marque=?");
             //Statement myStatement = connection.createStatement();
        ){
            //int ret = myStatement.executeUpdate("UPDATE MARQUE SET NOM='"+m.getNom()+"' WHERE id_marque="+m.getId_marque());
            prepStat.setString(1, m.getNom());
            prepStat.setInt(2, m.getId_marque());

            int ret = prepStat.executeUpdate();
            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du MarqueDAO update()");
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Integer idMarque) {
        try (// Conn, Stmnt sont des ATUCLOSABLE, en mettant entre parenthese(try with ressources), les ressources seront automatiquement fermées
             Connection connection = MySqlConnectionFactory.getConnection();
             PreparedStatement prepStat = connection.prepareStatement("DELETE FROM Marque WHERE id_marque=?")
             //Statement myStatement = connection.createStatement();
        ){
            //int ret = myStatement.executeUpdate("DELETE FROM Marque WHERE id_marque="+idMarque);
            prepStat.setInt(1, idMarque);
            int ret = prepStat.executeUpdate();

            return ret > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur lors du MarqueDAO delete()");
            ex.printStackTrace();
        }

        return false;
    }
}
