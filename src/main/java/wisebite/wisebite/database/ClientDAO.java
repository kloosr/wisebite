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
        String sql = "Insert into client(username, weight, height, start_date) values (?, ?, ?, ?);";
        jdbcTemplate.update(sql, client.getUsername());
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
