package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Client> findClientByDietitian(String dietitianUsername) {
        String sql = "SELECT u.username, u.firstname, u.infix, u.lastname FROM User u JOIN Client c ON u.username = c.username WHERE c.dietitian = ?";
        List<Client> resultList = jdbcTemplate.query(sql, new ClientRowMapper(), dietitianUsername);

        return resultList;
    }
    public Client findClientByUsername(String username) {
        String sql = "SELECT u.username, u.firstname, u.infix, u.lastname FROM User u JOIN Client c ON u.username = c.username WHERE u.username = ?";
        return jdbcTemplate.queryForObject(sql, new ClientRowMapper(), username);
    }

    public boolean isClientOnDietitianList(String username) {
        String sql = "SELECT COUNT(*) " +
                "FROM Client c " +
                "INNER JOIN User u ON c.username = u.username " +
                "JOIN Dietitian d ON c.dietitian = d.username " +
                "WHERE c.username = ? AND d.username = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count > 0;
    }
    private class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Client(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"),
                    resultSet.getDouble("weight"),
                    resultSet.getInt("height"));
        }
    }

}
