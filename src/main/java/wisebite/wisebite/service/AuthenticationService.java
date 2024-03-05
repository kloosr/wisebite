package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import wisebite.wisebite.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.User;
import wisebite.wisebite.model.UserInfo;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserDAO userDAO;
    private final String PEPPER = "6391d7b2b6b66fad6c9a0a09e42269eb";
    @Autowired
    public AuthenticationService (UserDAO userDAO) {this.userDAO = userDAO;}
    public Optional<User> findByUsername (String username) {
        return userDAO.findByUsername(username);
    }

    // Creates a hash from the password
    public String createHash(UserInfo userInfo) {
        String hash = BCrypt.hashpw(String.format(userInfo.getPassword() + PEPPER), BCrypt.gensalt());
        userInfo.setHash(hash);
        return hash;
    }
    public boolean checkPassword(String input, String hash) {
            return BCrypt.checkpw(String.format(input + PEPPER), hash);
        }
}
