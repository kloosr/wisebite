package wisebite.wisebite.repository;

import org.springframework.jdbc.core.RowMapper;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Plan;
import wisebite.wisebite.model.Workout;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyTaskRowMapper implements RowMapper<DailyTask> {


    @Override
    public DailyTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        DailyTask task = new DailyTask();

        // This is just a placeholder. TO DO: to implement the logic for fetching related entities.
        Plan plan = fetchPlanForTask(rs.getString("client"));
        Diet diet = fetchDietForTask(rs.getInt("id"));
        Workout workout = fetchWorkoutForTask(rs.getInt("id"));

        task.setPlan(plan);
        task.setDiet(diet);
        task.setWorkout(workout);
        task.setDailyGoal(rs.getBoolean("daily_goal"));

        return task;
    }

    private Plan fetchPlanForTask(String client) {
        //fetch plan from the database
        return new Plan();
    }
    private Diet fetchDietForTask(int id){
        //fetch plan from the database
        return new Diet();
    }

    private Workout fetchWorkoutForTask(int id){
        return new Workout();
    }
}
