package iva.DbUtility;

import iva.DAO.TeamDAO;
import iva.DAO.TeamDAOImplementation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public static Connection establishConnection() {
        Connection connection = null;

        Properties connectionProps = new Properties();
        connectionProps.put("dbms", "mysql");
        connectionProps.put("user", "root");
        connectionProps.put("password", "Ivaoy");
        connectionProps.put("useSSL", "false");

        String url = "jdbc:mysql://localhost:3306/fantasy";
        try {
            connection = DriverManager.getConnection(url, connectionProps);
//            System.out.println("connection was established");
        } catch (SQLException e) {
            System.out.println("no connection established");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeResources(Connection connection, PreparedStatement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            System.out.println("Could not close statement!");
            e.printStackTrace();
        }

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.out.println("Could not close connection!");
            e.printStackTrace();
        }

    }

    public static TeamDAO getTeamDAO(){
        return new TeamDAOImplementation();
    }


}