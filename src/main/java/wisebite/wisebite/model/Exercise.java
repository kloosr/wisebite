package wisebite.wisebite.model;

public class Exercise {
    // class attributes
    private String name;
    private String type;
    private int  reps;
    private int weightAmount;
    private int duration;

    // constructor
    public Exercise(String name, String type, int reps, int weightAmount, int duration){
        this.name = name;
        this.type = type;
        this.reps = reps;
        this.weightAmount = weightAmount;
        this.duration = duration;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getReps() {
        return reps;
    }

    public int getWeightAmount() {
        return weightAmount;
    }

    public int getDuration() {
        return duration;
    }
}

