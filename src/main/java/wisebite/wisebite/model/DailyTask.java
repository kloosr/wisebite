package wisebite.wisebite.model;

public class DailyTask {
    private final int id;
    Plan plan;
    Diet diet;
    Workout workout;
    private boolean dailyGoal;

    public DailyTask(Plan plan, Diet diet, Workout workout, boolean dailyGoal){
        this.id = 0;
        this.plan = plan;
        this.diet = diet;
        this.workout = workout;
        this.dailyGoal = dailyGoal;
    }


    public boolean setDailyGoal(){
        //TODO
        return true;
    }
}
