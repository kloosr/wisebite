package model;

public class Exercise {
    private String name;
    private String type;
    private int  reps;
    private int weightAmount;
    private int duration;

    public Exercise(String name, String type, int reps, int weightAmount, int duration){
        this.name = name;
        this.type = type;
        this.reps = reps;
        this.weightAmount = weightAmount;
        this.duration = duration;
    }
}

