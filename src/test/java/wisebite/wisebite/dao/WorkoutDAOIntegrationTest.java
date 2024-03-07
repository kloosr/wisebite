package wisebite.wisebite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import wisebite.wisebite.database.WorkoutDAO;

@SpringBootTest
@ActiveProfiles("test")
public class WorkoutDAOIntegrationTest {
    private final WorkoutDAO workoutDAO;
    @Autowired
    public WorkoutDAOIntegrationTest(WorkoutDAO workoutDAO) {
        this.workoutDAO = workoutDAO;
    }
}
