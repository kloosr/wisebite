package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Dietitian;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DietitianDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DietitianDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Dietitian findByUsername(String username) {
        String sql = "Select * from Dietitian where username = ?;";
        List<Dietitian> resultList =
                jdbcTemplate.query(sql, new DietitianRowMapper(), username);
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.getFirst();
        }
    }

    public void storeDietitian(Dietitian dietitian) {
        String sql = "Insert into dietitian values (?);";
        jdbcTemplate.update(sql, dietitian.getUsername());
    }

    public boolean dietitianExists(String dietitan) {
        String sql = "SELECT * FROM deititian d JOIN user u ON d.username = u.username WHERE d.username = ?";
        List<Dietitian> dietitianList = jdbcTemplate.query(sql, new DietitianRowMapper(), dietitan);
        return !dietitianList.isEmpty();
    }

    public void assignCoachToClient(String clientUsername, String coachUsername) {
        String sql = "UPDATE Client SET coach = ? WHERE username = ?";
        jdbcTemplate.update(sql, coachUsername, clientUsername);
    }

    private class DietitianRowMapper implements RowMapper<Dietitian> {
        @Override
        public Dietitian mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Dietitian(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"));
        }

    }
}