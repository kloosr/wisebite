package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Client;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ClientDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public ClientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Client findByUsername(String username) {
        String sql = "SELECT * FROM User LEFT JOIN Client ON User.username = Client.username WHERE User.username = ?;";
        List<Client> resultList =
                jdbcTemplate.query(sql, new ClientRowMapper(), username);
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.getFirst();
        }
    }

    public void storeClient(Client client) {
        jdbcTemplate.update(connection -> buildInsertUserStatement(client, connection));
    }

    private PreparedStatement buildInsertUserStatement(
            Client client, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "Insert into client(username, weight, height, start_date) values (?, ?, ?, ?);");
        ps.setString(1, client.getUsername());
        ps.setDouble(2, client.getWeight());
        ps.setInt(3, client.getHeight());
        ps.setDate(4, Date.valueOf(LocalDate.now()));
        return ps;
    }

    public List<Client> findClientsByDietitian(String dietitianUsername) {
        String sql = "SELECT u.username, u.password, u.firstname, u.infix, u.lastname, c.weight, c.height, c.start_date FROM User u JOIN Client c ON u.username = c.username WHERE c.dietitian = ?";
        return jdbcTemplate.query(sql, new ClientRowMapper(), dietitianUsername);
    }

    public Client findClientByUsername(String username) {
        String sql = "SELECT u.username, u.password, u.firstname, u.infix, u.lastname, c.weight, c.height, c.start_date FROM User u JOIN Client c ON u.username = c.username WHERE u.username = ?";
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

    public Double getWeight(String username) {
        String sql = "SELECT weight FROM Client WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, username);
    }
    public Double getHeight(String username) {
        String sql = "SELECT height FROM Client WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, username);
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
                    resultSet.getInt("height"),
                    resultSet.getDate("start_date"));
        }
    }
}