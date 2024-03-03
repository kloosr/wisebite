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

    public int getId() {
        return id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public boolean isDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(boolean dailyGoal) {
        this.dailyGoal = dailyGoal;
    }
}
