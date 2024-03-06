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

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final String PEPPER = "6391d7b2b6b66fad6c9a0a09e42269eb";
    private final UserDAO userDAO;
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
    public boolean checkPassword(String input, String hash) {
        return BCrypt.checkpw(String.format(input + PEPPER), hash);
    }

    public String login(String coach) {
        String jwtToken = JWT.create()
                .withIssuer("wisebite")
                .withSubject("wisebitedetails")
                .withClaim("role", "coach")
                .withClaim("name", coach)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(600))
                .sign(algorithm);
        return jwtToken;
    }

    public boolean hasAcces (String jwtToken) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        if (decodedJWT.getClaim("role").asString().equals("coach")) {
            return true;
        } else {
            return false;
        }
    }

    public String getUsername (String jwtToken) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaim("name").asString();
    }

    public String getRole (String jwtToken) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaim("role").asString();
    }
}
