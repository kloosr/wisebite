package wisebite.wisebite.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DailyTask {
    // 'final' removed. Assuming that id is meant to be a database-generated value (e.g., auto-incrementing),
    // it should not be final, because it will be set by JPA (Java Persistence API) when the entity is persisted,
    // not by your application code when the object is created.

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    Diet diet;
    Workout workout;
    private int dailyGoal;
    private Client client;

    public DailyTask(Date date, int dailyGoal, Diet diet, Workout workout, Client client ) {
        this.date = date;
        this.dailyGoal = dailyGoal;
        this.diet = diet;
        this.workout = workout;
        this.client = client;
    }
    public DailyTask(Date date, int dailyGoal, Workout workout, Diet diet) {
        this(date, dailyGoal, diet, workout, null);
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
