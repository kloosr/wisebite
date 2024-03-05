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
    // class attributes
    private final String NAME = "name";
    private final String ID = "id";
    private final String DURATION = "duration";
    private final String BURNED_CALORIES = "burned_calories";
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    //Constructor
    @Autowired
    public WorkoutDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {this.jdbcTemplate = jdbcTemplate; this.dataSource = dataSource;}

    // save method to save a workout in the Database and return a workout ID
    public int save(Workout workout) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.withTableName("workout").usingGeneratedKeyColumns("id");
        workout.setId(simpleJdbcInsert.executeAndReturnKey(mapInsertParameters(workout)).intValue());
        return workout.getId();
    }

    // getAll method that finds every workout in the database
    public List<Workout> getAll() {
        String sql = "SELECT * FROM wisebite.workout";
        return jdbcTemplate.query(sql, new WorkoutRowMapper());
    }

    // find a workout by Id
    public Workout getWorkoutById(int id){
        String sql = "SELECT * FROM wisebite.workout WHERE id = ?";
        List<Workout> workoutList = jdbcTemplate.query(sql, new WorkoutRowMapper(), id);
        //if the list with workouts is empty, return null
        if (workoutList.isEmpty()) {
            return null;
            // else, return the only workout in the list (its always 1 workout because they have unique id's)
        } else {
            return workoutList.get(0);
        }
    }

    // map a workout object so it can be inserted into the database
    private Map<String, Object> mapInsertParameters(Workout workout) {
        Map<String, Object> insertParameters = new HashMap<>();
        insertParameters.put(NAME, workout.getName());
        insertParameters.put(DURATION, workout.getDuration());
        insertParameters.put(BURNED_CALORIES, workout.getBurnedCalories());
        return insertParameters;
    }

    // Rowmapper that reads information from the database and creates a workout object
    private class WorkoutRowMapper implements RowMapper<Workout> {
        @Override
        public Workout mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Workout(rs.getInt(ID),
                    rs.getString(NAME),
                    rs.getInt(DURATION),
                    rs.getInt(BURNED_CALORIES));
        }
    }
}
