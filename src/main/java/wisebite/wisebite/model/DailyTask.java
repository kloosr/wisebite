package wisebite.wisebite.model;

import java.util.Date;

public class DailyTask {

    private Date date;
    Diet diet;
    Workout workout;
    private int dailyGoal;
    private Client client;

    public DailyTask(Date date, int dailyGoal, Client client, Workout workout, Diet diet) {
        this.date = date;
        this.dailyGoal = dailyGoal;
        this.diet = diet;
        this.workout = workout;
        this.client = client;
    }

    public DailyTask() {
    }

    public boolean setDailyGoal(){
        //TODO
        return true;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
