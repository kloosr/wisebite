package wisebite.wisebite.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Workout;

import java.util.Date;
public class DailyTaskDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private int dailyGoal;
    private Workout workout;
    private Diet diet;


    public DailyTaskDTO(Date date, int dailyGoal, Workout workout, Diet diet) {
        this.date = date;
        this.dailyGoal = dailyGoal;
        this.workout = workout;
        this.diet = diet;
    }

    public DailyTaskDTO(DailyTask dailyTask) {
        this(dailyTask.getDate(),
                dailyTask.getDailyGoal(),
                dailyTask.getWorkout(),
                dailyTask.getDiet());
    }

    public DailyTaskDTO convertToDTO(DailyTask dailyTask) {
        return new DailyTaskDTO(dailyTask.getDate(),
                dailyTask.getDailyGoal(),
                dailyTask.getWorkout(),
                dailyTask.getDiet());
    }
    public Date getDate() {
        return date;
    }

    public int getDailyGoal() {
        return dailyGoal;
    }

    public Workout getWorkout() {
        return workout;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

}
