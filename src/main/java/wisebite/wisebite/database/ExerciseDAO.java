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

@Repository
public class ExerciseDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public ExerciseDAO(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
    public List<Exercise> getAll() {
        String sql = "SELECT * FROM wisebite.exercise";
        return jdbcTemplate.query(sql, new ExerciseRowMapper());
    }
    public Optional<Exercise> getByName(String name){
        String sql = "SELECT * FROM wisebite.exercise WHERE name = ?";
        List<Exercise> resultList = jdbcTemplate.query(sql, new ExerciseRowMapper(), name);
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }
    public List<Exercise> getAllByWorkout (int id) {
        String sql = "SELECT e.name, e.type, e.reps, e.weight_amount, e.duration FROM exercise e LEFT JOIN exerciseworkout ew " +
                "ON e.name = ew.exercise WHERE workout = ?";
        List<Exercise> exerciseList = jdbcTemplate.query(sql, new ExerciseRowMapper(), id);
        return exerciseList;
    }

    private class ExerciseRowMapper implements RowMapper<Exercise> {

        @Override
        public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Exercise(rs.getString("name"),
                    rs.getString("type"),
                    rs.getInt("reps"),
                    rs.getInt("weight_amount"),
                    rs.getInt("duration"));
        }
    }
}
