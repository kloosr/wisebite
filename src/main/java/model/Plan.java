package model;

public class Plan {
    Client client;
    Coach coach;
    private int weightLossGoal;
    private boolean goalCompleted;
    private double duration;

    public Plan(Client client, Coach coach, int weightLossGoal){
        this.client = client;
        this.coach = coach;
        this.weightLossGoal = weightLossGoal;
    }

    public boolean endPlan(){
        //TODO
        return true;
    }

}
