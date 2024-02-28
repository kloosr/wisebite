package wisebite.wisebite.database;

import wisebite.wisebite.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDAO {

    JdbcTemplate jdbcTemplate;
    @Autowired
    public AdminDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Admin findByUsername(String username) {
        String sql = "Select * from Admin where username = ?;";
        List<Admin> resultList =
                jdbcTemplate.query(sql, new AdminRowMapper(), username);
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.getFirst();
        }
    }

    public void storeAdmin(Admin admin) {
        String sql = "Insert into admin values (?);";
        jdbcTemplate.update(sql, admin.getUsername());
    }

    private class AdminRowMapper implements RowMapper<Admin> {
        @Override
        public Admin mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Admin(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"));
        }
    }
}
