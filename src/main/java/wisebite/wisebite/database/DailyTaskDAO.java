package wisebite.wisebite.database;

import org.springframework.jdbc.core.JdbcTemplate;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.repository.DailyTaskRowMapper;

import java.util.Date;
import java.util.List;

public class DailyTaskDAO {

    JdbcTemplate jdbcTemplate;

    public DailyTaskDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static List<DailyTask> findByClientAndDate(String clientUsername, Date date){
        String sql = "SELECT * FROM DailyTask WHERE client = ? AND date = ?";
        return jdbcTemplate.query(sql, new Object[]{clientUsername,date}, new DailyTaskRowMapper());
    }

}
