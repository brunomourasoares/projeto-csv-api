package br.com.apicsv.repositories;

import java.sql.*;

public class DatabaseConnector {
    public static final String DRIVER;
    public static final String URL;
    public static final String IP;
    public static final String PORT;
    public static final String USERNAME;
    public static final String PASSWORD;
    public static final String DATABASE;

    static {
        DRIVER = "org.postgresql.Driver";
        URL = "jdbc:postgresql://";
        IP = "localhost";
        PORT = "5432";
        USERNAME = "root";
        PASSWORD = "Umabemdificil@2025";
        DATABASE = "cadastrocsv";
    }

    private DatabaseConnector() {
        throw new UnsupportedOperationException("Não é possível instanciar esta classe.");
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL + IP + ":" + PORT + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }
}