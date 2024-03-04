package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.Diet;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DietDAO {
    // final variables for RowMapper
    private final String ID = "id";
    private final String TYPE = "type";
    private final String CALORIES = "calorie_amount";
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    @Autowired
    public DietDAO(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }
    // get all method that returns a list of all diets in the database
    public List<Diet> getAll () {
        String sql = "SELECT * FROM diet";
        return jdbcTemplate.query(sql, new DietRowMapper());
    }
    // gets a single diet from the database based on id
    public Optional<Diet> getById (int id) {
        String sql = "SELECT * FROM diet WHERE id = ?";
        List<Diet> tempDietList = jdbcTemplate.query(sql, new DietRowMapper(), id);
        if (tempDietList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(tempDietList.get(0));
        }
    }
    private class DietRowMapper implements RowMapper<Diet> {

        @Override
        public Diet mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Diet(rs.getInt(ID), rs.getString(TYPE), rs.getInt(CALORIES));
        }
    }
}
