package wisebite.wisebite.model;

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

    public Plan() {
        this.goalCompleted = false;// A new plan is not completed by default
        this.duration = 0;// Default to zero if the duration is not yet known
        // Client and Coach would be null by default if not set
    }

    public boolean endPlan(){
        //TODO
        return true;
    }

}
