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
    public void createHash(UserInfo userInfo) {
        userInfo.setHash(BCrypt.hashpw(userInfo.getPassword() + PEPPER, BCrypt.gensalt()));
    }
    public boolean checkPassword(User user, String input) {
        Optional<User> retrievedUser = findByUsername(user.getUsername());
        if (retrievedUser.isPresent()) {
            String hash = retrievedUser.get().getPassword();
            return BCrypt.checkpw(input + PEPPER, hash);
        } else {
            return false;
        }
    }

}
