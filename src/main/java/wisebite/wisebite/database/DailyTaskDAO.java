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

    public List<DailyTask> findByClient(String clientUsername){
        String sql = "SELECT * FROM DailyTask WHERE client = ?";
        return jdbcTemplate.query(sql, new DailyTaskRowMapper(), clientUsername);
    }

    public List<DailyTask> findByClientWithoutClientInfo(String clientUsername){
        String sql = "SELECT date, daily_goal, workout_id, diet_id FROM DailyTask WHERE client = ?";
        return jdbcTemplate.query(sql, new DailyTaskRowMapperNoInfo(), clientUsername);
    }

    private class DailyTaskRowMapper implements RowMapper<DailyTask> {
        // TODO ask michel if this is the way
        ClientDAO clientDAO = new ClientDAO(jdbcTemplate);
        ExerciseDAO exerciseDAO = new ExerciseDAO(jdbcTemplate);
        WorkoutDAO workoutDAO = new WorkoutDAO(jdbcTemplate, dataSource);
        DietDAO dietDAO = new DietDAO(jdbcTemplate, dataSource);
        RecipeDAO recipeDAO = new RecipeDAO(jdbcTemplate, dataSource);
        WorkoutRepository workoutRepository = new WorkoutRepository(workoutDAO, exerciseDAO);
        DietRepository dietRepository = new DietRepository(dietDAO, recipeDAO);
        @Override
        public DailyTask mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DailyTask(rs.getDate(DATE),
                    rs.getInt(DAILY_GOAL),
                    clientDAO.findByUsername(rs.getString(CLIENT)),
                    workoutRepository.createWorkout(rs.getInt(WORKOUT_ID)),
                    dietRepository.createDietById(rs.getInt(DIET_ID)));
        }
    }

    private class DailyTaskRowMapperNoInfo implements RowMapper<DailyTask> {
        // TODO ask michel if this is the way
        ExerciseDAO exerciseDAO = new ExerciseDAO(jdbcTemplate);
        WorkoutDAO workoutDAO = new WorkoutDAO(jdbcTemplate, dataSource);
        DietDAO dietDAO = new DietDAO(jdbcTemplate, dataSource);
        RecipeDAO recipeDAO = new RecipeDAO(jdbcTemplate, dataSource);
        WorkoutRepository workoutRepository = new WorkoutRepository(workoutDAO, exerciseDAO);
        DietRepository dietRepository = new DietRepository(dietDAO, recipeDAO);
        @Override
        public DailyTask mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DailyTask(rs.getDate(DATE),
                    rs.getInt(DAILY_GOAL),
                    workoutRepository.createWorkout(rs.getInt(WORKOUT_ID)),
                    dietRepository.createDietById(rs.getInt(DIET_ID)));
        }
    }
}
