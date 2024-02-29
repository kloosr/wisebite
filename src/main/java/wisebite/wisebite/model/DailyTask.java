package wisebite.wisebite.model;

public class DailyTask {
    private final int id;
    Plan plan;
    Meal meal;
    Workout workout;
    private boolean dailyGoal;

    public DailyTask(Plan plan, Meal meal, Workout workout, boolean dailyGoal){
        this.id = 0;
        this.plan = plan;
        this.meal = meal;
        this.workout = workout;
        this.dailyGoal = dailyGoal;
    }


    public boolean setDailyGoal(){
        //TODO
        return true;
    }
}
