package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Exercise;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// this class is used to add or fetch exercise data from the database (written by Mark Fijn)
@Repository
public class ExerciseDAO {
    // class attributes
    private final String NAME = "name";
    private final String TYPE = "type";
    private final String REPS = "reps";
    private final String WEIGHT = "weight_amount";
    private final String DURATION = "duration";

    // JdbcTemplate for Spring injection
    private final JdbcTemplate jdbcTemplate;

    // constructor
    @Autowired
    public ExerciseDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    // getAll method that fetches every single exercise in the database
    public List<Exercise> getAll() {
        String sql = "SELECT * FROM wisebite.exercise";
        return jdbcTemplate.query(sql, new ExerciseRowMapper());
    }

    // getByName method that fetches a single Exercise by workoutName from the database
    public Optional<Exercise> getByName(String name){
        String sql = "SELECT * FROM wisebite.exercise WHERE name = ?";
        List<Exercise> resultList = jdbcTemplate.query(sql, new ExerciseRowMapper(), name);
        // if list is empty, return an empty optional
        if (resultList.isEmpty()) {
            return Optional.empty();
        // else return the optional of the online exercise in the list (exercise names are unique)
        } else {
            return Optional.of(resultList.get(0));
        }
    }

    // getAllByWorkout method that fetches every exercise that is linked to a given workout id
    public List<Exercise> getAllByWorkout (int id) {
        String sql = "SELECT e.name, e.type, e.reps, e.weight_amount, e.duration FROM exercise e LEFT JOIN exerciseworkout ew " +
                "ON e.name = ew.exercise WHERE workout = ?";
        List<Exercise> exerciseList = jdbcTemplate.query(sql, new ExerciseRowMapper(), id);
        return exerciseList;
    }

    // RowMapper that read data from the database and creates an exercise object.
    private class ExerciseRowMapper implements RowMapper<Exercise> {
        @Override
        public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Exercise(rs.getString(NAME),
                    rs.getString(TYPE),
                    rs.getInt(REPS),
                    rs.getInt(WEIGHT),
                    rs.getInt(DURATION));
        }
    }
}
