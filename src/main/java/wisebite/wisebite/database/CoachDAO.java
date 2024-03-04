package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Coach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoachDAO {
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String FIRSTNAME = "firstname";
    private final String INFIX = "infix";
    private final String LASTNAME = "lastname";
    JdbcTemplate jdbcTemplate;
    @Autowired
    public CoachDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Coach findByUsername(String username) {
        String sql = "Select * from Coach where username = ?;";
        List<Coach> resultList =
                jdbcTemplate.query(sql, new CoachRowMapper(), username);
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.getFirst();
        }
    }
    public List<Coach> getAllCoaches(){
        String sql = "SELECT * FROM User JOIN Coach ON user.username = coach.username";
        return jdbcTemplate.query(sql, new CoachRowMapper());
    }

    public void storeCoach(Coach coach) {
        String sql = "Insert into coach(username) values (?);";
        jdbcTemplate.update(sql, coach.getUsername());
    }

    private class CoachRowMapper implements RowMapper<Coach> {
        @Override
        public Coach mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Coach(resultSet.getString(USERNAME),
                    resultSet.getString(PASSWORD),
                    resultSet.getString(FIRSTNAME),
                    resultSet.getString(INFIX),
                    resultSet.getString(LASTNAME));
        }
    }
}
