package wisebite.wisebite.database;

import wisebite.wisebite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<User> findByUsername(String username) {
        String sql = "Select * from User where username = ?;";
        List<User> resultList =
                jdbcTemplate.query(sql, new UserRowMapper(), username);
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.getFirst());
        }
    }
    public void storeUser(User user) {
        jdbcTemplate.update(connection ->
            buildInsertUserStatement(user, connection));
    }
    public void deleteUser(String username) {
        String sql = "DELETE FROM User WHERE username = ?;";
        jdbcTemplate.update(sql, username);
    }
    private PreparedStatement buildInsertUserStatement(
            User user, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "Insert into user(username, password, firstname, infix, lastname) values (?,?,?,?,?)");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getInfix());
        ps.setString(5, user.getLastName());
        return ps;
    }
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new User(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"));
        }
    }

}
