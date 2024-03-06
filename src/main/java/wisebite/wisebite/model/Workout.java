package wisebite.wisebite.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    // attributes
    private int id;
    private String name;
    private int duration;
    private int burnedCalories;
    private List<Exercise> exerciseList = new ArrayList<>();

    // all args constructor
    public Workout(int id, String name, int duration, int burnedCalories, List<Exercise> exerciseList){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
        this.setExerciseList(exerciseList);
    }

    // constructor without exerciseList
    public Workout(int id, String name, int duration, int burnedCalories){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
        this.setExerciseList(null);
    }

    // default constructor
    public Workout() {
        this(0, "default", 0, 0, null);
    }
    // toString method
    public String toString() {
        return name + ", duration: " + duration + " minutes, number of calories burned: " + burnedCalories;
    }

    // getters and needed setters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
}
