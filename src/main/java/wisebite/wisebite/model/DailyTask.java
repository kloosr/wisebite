package wisebite.wisebite.model;

public class DailyTask {
    Plan plan;
    Diet diet;
    Workout workout;
    private boolean dailyGoal;

    public DailyTask(Plan plan, Diet diet, Workout workout, boolean dailyGoal){
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
