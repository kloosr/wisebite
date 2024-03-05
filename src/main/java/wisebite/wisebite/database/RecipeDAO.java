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

// this class is used for saving, getting and handling Recipe data in and from the database
@Repository
public class RecipeDAO {
    // Class attributes
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final String CALORIES = "calorie_amount";
    private final String INSTRUCTIONS = "instructions";

    // JdbcTemplate and DataSource for spring injection
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    // constructor
    @Autowired
    public RecipeDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    // getAll method to fetch every single Recipe from the database
    public List<Recipe> getAll() {
        String sql = "SELECT * FROM recipe";
        return jdbcTemplate.query(sql, new RecipeRowMapper());
    }

    // getAllByDiet method that creates a List of every Recipe corresponding to a given recipe Id
    public List<Recipe> getAllByDiet (int id) {
        String sql = "SELECT r.name, r.description, r.calorie_amount, r.instructions FROM recipe r" +
                " LEFT JOIN meal m ON r.name = m.recipe WHERE id = ?";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), id);
    }


    // Rowmapper to read data and create a Recipe object
    private class RecipeRowMapper implements RowMapper<Recipe> {

        @Override
        public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Recipe(rs.getString(NAME), rs.getString(DESCRIPTION),
            rs.getInt(CALORIES), rs.getString(INSTRUCTIONS));
        }
    }
}


