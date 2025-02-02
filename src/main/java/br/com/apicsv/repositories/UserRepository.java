package br.com.apicsv.repositories;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.sql.*;

@ApplicationScoped
public class UserRepository {

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet result;

    public UserRepository() {
    }

    public void importDataFromCSV(String filePath) throws IOException, SQLException {
        String sql = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";
        conn = DatabaseConnector.connect();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    stmt.setString(1, values[0]);
                    stmt.setString(2, values[1]);
                    stmt.setString(3, values[2]);
                    stmt.addBatch();
                }
                stmt.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public void exportDataToTXT(String filePath) throws IOException, SQLException {
        String selectSQL = "SELECT * FROM users";
        BufferedWriter bw = null;

        bw = new BufferedWriter(new FileWriter(filePath));

        conn = DatabaseConnector.connect();
        pst = conn.prepareStatement(selectSQL);
        result = pst.executeQuery();

        while (result.next()) {
            bw.write("#" + result.getInt("id") + "#" + result.getString("name") + "#" + result.getString("email") + "#" + result.getString("phone"));
            bw.newLine();
        }
        bw.close();
    }
}
