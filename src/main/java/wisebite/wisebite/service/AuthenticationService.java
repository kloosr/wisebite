package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import wisebite.wisebite.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.User;
import wisebite.wisebite.model.UserTypeEnum;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final String PEPPER = "6391d7b2b6b66fad6c9a0a09e42269eb";
    private final UserDAO userDAO;
    private final int ONEDAY = 86400;
    Algorithm algorithm = Algorithm.HMAC256("wisebite");
    JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("wisebite").build();
    @Autowired
    public AuthenticationService (UserDAO userDAO) {this.userDAO = userDAO;
    }
    public Optional<User> findByUsername (String username) {
        return userDAO.findByUsername(username);
    }

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
    public boolean checkPassword(String input, String username) {
        return BCrypt.checkpw(String.format(input + PEPPER), userDAO.getPasswordHash(username));
    }

    public String getToken(String username) {
        User user = userDAO.findByUsername(username).get();
        String jwtToken = JWT.create()
                .withIssuer("wisebite")
                .withSubject("UserInfo")
                .withClaim("role", user.getUserType().toString())
                .withClaim("username", user.getUsername())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(ONEDAY))
                .sign(algorithm);
        return jwtToken;
    }

    public boolean hasAccess (String jwtToken, UserTypeEnum userTypeEnum) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        String userType = userTypeEnum.toString();
        if (decodedJWT.getClaim("role").asString().equals(userType)) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername (String jwtToken) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaim("username").asString();
    }

    public String getRole (String jwtToken) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaim("role").asString();
    }
}
