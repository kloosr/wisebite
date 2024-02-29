package wisebite.wisebite.database;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CoachDAO {
    public static class CoachRowMapper implements RowMapper<Coach> {
        @Override
        public Coach mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Coach(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"));
        }
    }
}
