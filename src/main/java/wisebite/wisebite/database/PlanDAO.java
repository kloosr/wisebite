package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Plan;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanDAO {
    private final String WEIGHT_LOSS_GOAL = "weight_loss_goal";
    private final String GOAL = "plan_goal";
    private final String DURATION = "duration";
    private final String CLIENT = "client";

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    @Autowired
    public PlanDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }
    public Plan getByClient (String username) {
        String sql = "SELECT * FROM wisebite.plan WHERE client = ?";
        List<Plan> planList = jdbcTemplate.query(sql, new PlanRowMapper(), username);
        if (!planList.isEmpty()) {
            return planList.getFirst();
        } else {
            return null;
        }
    }

    private class PlanRowMapper implements RowMapper<Plan> {
        // TODO ask Michel if this is the way
        ClientDAO clientDAO = new ClientDAO(jdbcTemplate);
        @Override
        public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Plan(rs.getInt(WEIGHT_LOSS_GOAL),
                    rs.getInt(GOAL), rs.getInt(DURATION),
                    clientDAO.findByUsername(rs.getString(CLIENT)));
        }
    }
}
