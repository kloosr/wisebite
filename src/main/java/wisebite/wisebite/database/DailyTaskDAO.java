package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.repository.WorkoutRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class DailyTaskDAO {
    private final String DATE = "date";
    private final String DAILY_GOAL = "daily_goal";
    private final String CLIENT = "client";
    private final String WORKOUT_ID = "workout_id";
    private final String DIET_ID = "diet_id";

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
        ClientDAO clientDAO = new ClientDAO(jdbcTemplate);
        WorkoutDAO workoutDAO = new WorkoutDAO(jdbcTemplate, dataSource);
        DietDAO dietDAO = new DietDAO(jdbcTemplate, dataSource);
        @Override
        public DailyTask mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DailyTask(rs.getDate(DATE),
                    rs.getInt(DAILY_GOAL),
                    clientDAO.findByUsername(rs.getString(CLIENT)),
                    workoutDAO.getWorkoutById(rs.getInt(WORKOUT_ID)),
                    dietDAO.getById(rs.getInt(DIET_ID)));
        }
    }
}
