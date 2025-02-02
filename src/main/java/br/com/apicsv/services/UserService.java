package br.com.apicsv.services;

import br.com.apicsv.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;

@ApplicationScoped
public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserService () {
    }

    public void exportDataToTXT(String filePath) throws IOException, SQLException {
        userRepository.exportDataToTXT(filePath);
    }

    public void importDataFromCSV(String filePath) throws IOException, SQLException {
        userRepository.importDataFromCSV(filePath);
    }
}
