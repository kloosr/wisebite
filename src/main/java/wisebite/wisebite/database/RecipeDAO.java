package wisebite.wisebite.database;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Recipe;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Set;

@Repository
public class RecipeDAO {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    public RecipeDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }
    public Set<Recipe> getAll() {
        String sql = "SELECT * FROM recipe";
        return null;
    }
}
