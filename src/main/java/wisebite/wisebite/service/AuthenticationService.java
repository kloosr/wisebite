package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwt;
import wisebite.wisebite.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.User;
import wisebite.wisebite.model.UserInfo;
import wisebite.wisebite.repository.ClientRepository;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private final String PEPPER = "6391d7b2b6b66fad6c9a0a09e42269eb";
    private final UserDAO userDAO;
    private final ClientRepository clientRepository;
    Algorithm algorithm = Algorithm.HMAC256("wisebite");
    JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("wisebite").build();
    @Autowired
    public AuthenticationService (UserDAO userDAO, ClientRepository clientRepository) {this.userDAO = userDAO; this.clientRepository = clientRepository;}
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
    /**
     * This method checks the input String against the correct hash String
     * @param input The user's input that is checked against the correct password
     * @param hash The hashed password
     */
    public boolean checkPassword(String input, String hash) {
            return BCrypt.checkpw(String.format(input + PEPPER), hash);
        }
}
