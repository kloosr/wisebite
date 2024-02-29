package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import wisebite.wisebite.database.UserDAO;
import wisebite.wisebite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserDAO userDAO;
    @Autowired
    public AuthenticationService (UserDAO userDAO) {this.userDAO = userDAO;}
    public Optional<User> findByUsername (String username) {
        return userDAO.findByUsername(username);
    }

}
