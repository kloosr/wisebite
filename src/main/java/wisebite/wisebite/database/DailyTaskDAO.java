package wisebite.wisebite.database;

import org.springframework.jdbc.core.JdbcTemplate;

public class DailyTaskDAO {
    JdbcTemplate jdbcTemplate;

    public DailyTaskDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
