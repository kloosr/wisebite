package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Recipe;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RecipeDAO {
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String CALORIES = "calorie_amount";
    private final String INSTRUCTIONS = "instructions";
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    @Autowired
    public RecipeDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }
    public List<Recipe> getAll() {
        String sql = "SELECT * FROM recipe";
        return jdbcTemplate.query(sql, new RecipeRowMapper());
    }

    public List<Recipe> getAllByDiet (int id) {
        String sql = "SELECT r.name, r.description, r.calorie_amount, r.instructions FROM recipe r" +
                " LEFT JOIN meal m ON r.name = m.recipe WHERE id = ?";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), id);
    }

    private class RecipeRowMapper implements RowMapper<Recipe> {

        @Override
        public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Recipe(rs.getString(NAME), rs.getString(DESCRIPTION),
            rs.getInt(CALORIES), rs.getString(INSTRUCTIONS));
        }
    }
}


