package wisebite.wisebite.model;

public class WorkoutDTO {
    private String name;
    private int duration;
    private int caloriesBurned;
    public WorkoutDTO(String name, int duration, int caloriesBurned) {
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}
