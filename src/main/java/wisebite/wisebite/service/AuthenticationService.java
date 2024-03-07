package wisebite.wisebite.service;

import org.hibernate.grammars.hql.HqlParser;
import org.mindrot.jbcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wisebite.wisebite.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.User;
import wisebite.wisebite.model.UserTypeEnum;
import wisebite.wisebite.repository.AdminRepository;
import wisebite.wisebite.model.UserTypeEnum;

import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final String PEPPER = "6391d7b2b6b66fad6c9a0a09e42269eb";
    private final AdminRepository adminRepository;
    private final String LOGIN_FAIL = "Incorrect username and/or password.";
    private final int ONEDAY = 86400;
    Algorithm algorithm = Algorithm.HMAC256("wisebite");
    JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("wisebite").build();
    @Autowired
    public AuthenticationService (AdminRepository adminRepository) {this.adminRepository = adminRepository;
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

    public ResponseEntity<?> login(String username, String inputPassword) {
        ResponseEntity<?> response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(LOGIN_FAIL);
        Optional<User> user = adminRepository.getUserByUsername(username);
        if (user.isPresent()) {
            String password = user.get().getPassword();
            if (checkPassword(inputPassword, password)) {
                String token = createToken(username, user.get().getUserType());
                response = ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
            }
        } return response;
    }
    public String createToken(String username, UserTypeEnum userType) {
        return JWT.create()
                .withIssuer("wisebite")
                .withSubject("UserInfo")
                .withClaim("role", userType.toString())
                .withClaim("username", username)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(ONEDAY))
                .sign(algorithm);
    }

    public boolean hasAccess (String jwtToken, UserTypeEnum userType) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT.getClaim("role").asString().equals(userType.toString());
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
