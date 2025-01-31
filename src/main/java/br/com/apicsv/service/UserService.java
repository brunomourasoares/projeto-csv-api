package br.com.apicsv.service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    @Resource(name = "jdbc/cadastrocsv")
    private DataSource dataSource;

    @Transactional
    public void importDataFromCSV(String filePath) throws Exception {
        try (Connection conn = dataSource.getConnection();
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String sql = "INSERT INTO users (name, email, phone) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, values[0]);
                    stmt.setString(2, values[1]);
                    stmt.setString(3, values[2]);
                    stmt.executeUpdate();
                }
            }
        }
    }

    public void exportDataToTXT(String filePath) throws Exception {
        try (Connection conn = dataSource.getConnection();
             BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            String sql = "SELECT * FROM users";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String line = rs.getInt("id") + "#" +
                            rs.getString("name") + "#" +
                            rs.getString("email") + "#" +
                            rs.getString("phone");
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
    }
}