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
    private final String PEPPER = "6391d7b2b6b66fad6c9a0a09e42269eb";
    public AuthenticationService () {}

    /**
     * Creates a hash from a plain text password
     * @param password The plain textfield that needs hashing
     * @return Hashed password
     */
    public String createHash(String password) {
        return BCrypt.hashpw(String.format(password + PEPPER), BCrypt.gensalt());
    }

    /**
     * This method checks the input String against the correct hash String
     * @param input The user's input that is checked against the correct password
     * @param hash The hashed password
     */
    public boolean checkPassword(String input, String hash) {
            return BCrypt.checkpw(String.format(input + PEPPER), hash);
        }
}
