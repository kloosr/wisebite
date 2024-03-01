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
        List<Client> resultList = jdbcTemplate.query(sql, new ClientWrapMapper(), dietitianUsername);

        return resultList;
    }
    public Client findClientByUsername(String username) {
        String sql = "SELECT u.username, u.firstname, u.infix, u.lastname FROM User u JOIN Client c ON u.username = c.username WHERE u.username = ?";
        return jdbcTemplate.queryForObject(sql, new ClientWrapMapper(), username);
    }
    private class ClientWrapMapper implements RowMapper<Client> {
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
