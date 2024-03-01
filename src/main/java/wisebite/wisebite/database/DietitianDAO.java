package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.*;

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
    public List<Client> findClientsByDietitianUsername(String dietitianUsername) {
        String sql = "SELECT u.* FROM user u " +
                "LEFT JOIN client c ON u.username = c.username " +
                "WHERE c.dietitian_username = ?";

        return jdbcTemplate.query(sql, new ClientDAO.clientWrapmapper, dietitianUsername);
    }

    public List<User> findCoachesByDietitianUsername(String dietitianUsername) {
        String sql = "SELECT u.* FROM user u " +
                "INNER JOIN coach c ON u.username = c.username " +
                "WHERE c.dietitian_username = ?";

        return jdbcTemplate.query(sql, new UserDAO.UserRowMapper(), dietitianUsername);
    }

    public void storeDietitian(Dietitian dietitian) {
        String sql = "Insert into dietitian values (?);";
        jdbcTemplate.update(sql, dietitian.getUsername());
    }
    public void updateDietitian(){
        //TODO
    }
    public void deleteDietitian(){
        //TODO
    }


    public void getAllDietitians(){
        //TODO
    }

    public void createDiet(){
        //TODO
    }

    public void createReceipe(){
        //TODO
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
