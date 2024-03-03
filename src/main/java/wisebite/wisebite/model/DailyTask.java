package wisebite.wisebite.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class DailyTask {
    // 'final' removed. Assuming that id is meant to be a database-generated value (e.g., auto-incrementing),
    // it should not be final, because it will be set by JPA (Java Persistence API) when the entity is persisted,
    // not by your application code when the object is created.
    @Setter
    @Getter
    private Date date;
    private int id;
    @Setter
    Plan plan;
    @Setter
    Diet diet;
    @Setter
    Workout workout;
    @Setter
    private boolean dailyGoal;

    public DailyTask(Plan plan, Diet diet, Workout workout, boolean dailyGoal){
        this.id = 0;
        this.plan = plan;
        this.diet = diet;
        this.workout = workout;
        this.dailyGoal = dailyGoal;
    }

    public DailyTask() {
    }


    public boolean setDailyGoal(){
        //TODO
        return true;
    }

}
