package wisebite.wisebite.service;

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
    private final UserDAO userDAO;
    Algorithm algorithm = Algorithm.HMAC256("wisebite");
    @Autowired
    public AuthenticationService (UserDAO userDAO) {this.userDAO = userDAO;}
    public Optional<User> findByUsername (String username) {
        return userDAO.findByUsername(username);
    }

    public String login() {
        String jwtToken = JWT.create()
                .withIssuer("wisebite")
                .withSubject("wisebitedetails")
                .withClaim("role", "coach")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(600))
                .sign(algorithm);
        return jwtToken;
    }

    public boolean hasAcces (String jwtToken) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("wisebite").build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        if (decodedJWT.getClaim("role").asString().equals("coach")) {
            return true;
        } else {
            return false;
        }
    }
}
