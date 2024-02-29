package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.database.ClientDAO;
import wisebite.wisebite.database.UserDAO;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.User;

import java.util.Optional;

@Service
public class UserManagementService {
    private UserDAO userDAO;

    public Optional<Client> getClientByUsername(String username) {
       return  ClientDAO.getClientByUsername(username);
    }

    public boolean isClientAssignedToDoctor(String username, String doctorUsername) {

        return false;
    }
}


