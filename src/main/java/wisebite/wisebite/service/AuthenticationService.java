package wisebite.wisebite.service;

import wisebite.wisebite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import wisebite.wisebite.repository.JdbcUserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final JdbcUserDAO jdbcUserDAO;
    @Autowired
    public AuthenticationService (JdbcUserDAO jdbcUserDAO) {this.jdbcUserDAO = jdbcUserDAO;}
    public Optional<User> findByUsername (String username) {
        return jdbcUserDAO.findByUsername(username);
    }
}
