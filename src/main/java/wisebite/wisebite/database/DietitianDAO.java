package wisebite.wisebite.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DietitianDAO {

    public void updateDietitian(){
        //TODO
    }
    public void deleteDietitian() {
        //TODO
    }


    public void getAllDietitians(){
        //TODO
    }

    public void createDiet(){
        //TODO
    }

    public void createReceipe(){
        //TODO
    }



    private class DietitianRowMapper implements RowMapper<Dietitian> {
        @Override
        public Dietitian mapRow(ResultSet resultSet, int rowNumber)
                throws SQLException {
            return new Dietitian(resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("firstname"),
                    resultSet.getString("infix"),
                    resultSet.getString("lastname"));
        }
    }
}
