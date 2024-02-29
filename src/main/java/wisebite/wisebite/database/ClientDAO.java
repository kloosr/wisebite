package wisebite.wisebite.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Admin;
import wisebite.wisebite.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientDAO {
    private final JdbcTemplate jdbcTemplate;

    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static Optional<Client> getClientByUsername(String username) {
        List<Client> members =
                jdbcTemplate.query("SELECT u.username, u.firstname, u.infix, u.lastname, " +
                        "c.weight, c.height, c.start_date, c.dietitian_username " +
                        "FROM user u LEFT JOIN client c ON u.username = c.username " +
                        "WHERE u.username = ?", new ClientRowMapper(), username);
        if (members.size() != 1) {
            return Optional.empty();
        } else {
            return Optional.of(members.get(0));
        }
    }

  /*  public Client getClientByUsername(String username) {
        String sql = "SELECT u.username, u.firstname, u.infix, u.lastname, " +
                "c.weight, c.height, c.start_date, c.dietitian_username " +
                "FROM user u LEFT JOIN client c ON u.username = c.username " +
                "WHERE u.username = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new ClientRowMapper());
    } */

    public static class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Client(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"));
        }
    }
}
