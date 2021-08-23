package jdbc;

import Entities.Marque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionJDBC {

    private static Connection conn = null;//MySQL driver MySQL Connector

    public static void main(String... args){

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/technobel?useSSL=false","COURSDB","COURSDB");

            List<Marque> listMarques = marquesFromDB();
            System.out.println("Marque(s) lue(s) dans la DB:");
            listMarques.forEach(System.out::println);

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Marque> marquesFromDB() throws SQLException {

        List<Marque> listRes = new ArrayList<>();
        Statement myStatement = conn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT * FROM MARQUE");

        while (rs.next()){

            int id = rs.getInt("id_marque");
            String nom = rs.getString("nom");
            Marque marque = new Marque(id, nom);
            listRes.add(marque);

        }

        if (listRes.size() == 0)
            return null;
        else
            return listRes;

    }

}
