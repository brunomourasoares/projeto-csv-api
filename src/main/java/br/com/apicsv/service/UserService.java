package br.com.apicsv.service;

import br.com.apicsv.model.User;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
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

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bw.write("#" + rs.getString("name") + ";#" + rs.getString("email") + ";#" + rs.getString("phone"));
                bw.newLine();
            }
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                users.add(user);
            }
            return users;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return users;
    }
}