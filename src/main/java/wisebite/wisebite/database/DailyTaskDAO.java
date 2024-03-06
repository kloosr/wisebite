package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.repository.DietRepository;
import wisebite.wisebite.repository.WorkoutRepository;
import wisebite.wisebite.repository.WorkoutRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class DailyTaskDAO {
    private final String DATE = "date";
    private final String DAILY_GOAL = "daily_goal";

    JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    @Autowired
    public DailyTaskDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }



    public List<DailyTask> getDailyTaskForClient(String clientUsername){
        String sql = "SELECT * FROM DailyTask WHERE client = ?";
        return jdbcTemplate.query(sql, new DailyTaskRowMapper(), clientUsername);
    }

    private class DailyTaskRowMapper implements RowMapper<DailyTask> {
        @Override
        public DailyTask mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DailyTask(rs.getDate(DATE),
                    rs.getInt(DAILY_GOAL), null, null, null);
        }
    }
}
