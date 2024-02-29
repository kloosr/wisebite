package wisebite.wisebite.repository;

import wisebite.wisebite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcUserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    @Autowired
    public JdbcUserDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    public void storeUser(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.execute(mapInsertParameters(user));
    }

    public Optional<User> findByUsername (String username) {
        String sql = "SELECT * FROM wisebite.user WHERE username = ?";
        List<User> result = jdbcTemplate.query(sql, new UserRowmapper(), username);
        if (result.size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of(result.get(0));
        }
    }

    private Map<String, Object> mapInsertParameters (User user) {
        Map<String, Object> insertParameters = new HashMap<>();
        insertParameters.put("username", user.getUsername());
        insertParameters.put("password", user.getPassword());
        insertParameters.put("firstname", user.getFirstName());
        insertParameters.put("infix", user.getInfix());
        insertParameters.put("lastname", user.getLastName());
        return insertParameters;
    }
}
