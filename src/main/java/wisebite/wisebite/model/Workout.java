package wisebite.wisebite.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private final int id;
    private String name;
    private int duration;
    private int burnedCalories;
    public Workout(String name, int duration, int burnedCalories) {
        this.id = 0;
        this.name = name;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
    }

    public String toString() {
        return name + ", duratioin: " + duration + " minutes, number of calories burned: " + burnedCalories;
    }
}
