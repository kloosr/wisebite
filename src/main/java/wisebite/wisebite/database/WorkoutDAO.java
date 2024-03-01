package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Workout;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class WorkoutDAO {
    private final String NAME = "name";
    JdbcTemplate jdbcTemplate;
    DataSource dataSource;
    @Autowired
    public WorkoutDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {this.jdbcTemplate = jdbcTemplate; this.dataSource = dataSource;}
    public int save(Workout workout) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.withTableName("workout").usingGeneratedKeyColumns("id");
        workout.setId(simpleJdbcInsert.executeAndReturnKey(mapInsertParameters(workout)).intValue());
        return workout.getId();
    }
    public List<Workout> getAll() {
        String sql = "SELECT * FROM wisebite.workout";
        return jdbcTemplate.query(sql, new WorkoutRowMapper());
    }
    public Optional<Workout> getWorkoutById(int id){
        String sql = "SELECT * FROM wisebite.workout WHERE id = ?";
        List<Workout> workoutList = jdbcTemplate.query(sql, new WorkoutRowMapper(), id);
        if (workoutList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(workoutList.get(0));
        }
    }
    private Map<String, Object> mapInsertParameters(Workout workout) {
        Map<String, Object> insertParameters = new HashMap<>();
        insertParameters.put(NAME, workout.getName());
        insertParameters.put("duration", workout.getDuration());
        insertParameters.put("burned_calories", workout.getBurnedCalories());
        return insertParameters;
    }
    private class WorkoutRowMapper implements RowMapper<Workout> {
        @Override
        public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Workout(rs.getInt("id"),
                    rs.getString(NAME),
                    rs.getInt("duration"),
                    rs.getInt("burned_calories"));
        }
    }
}
