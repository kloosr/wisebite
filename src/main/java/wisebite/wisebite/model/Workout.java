package wisebite.wisebite.model;

public class Workout {
    private int id;
    private String name;
    private int duration;
    private int burnedCalories;
    public Workout(int id, String name, int duration, int burnedCalories){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
    }
    public String toString() {
        return name + ", duration: " + duration + " minutes, number of calories burned: " + burnedCalories;
    }

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
}
