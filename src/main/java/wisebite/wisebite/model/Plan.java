package wisebite.wisebite.model;

import java.util.List;

public class Plan {
    private Client client;
    private int weightLossGoal;
    private int goalCompleted;
    private int duration;
    private List<DailyTask> taskList;

    public Plan(int weightLossGoal, int goalCompleted, int duration, Client client, List<DailyTask> taskList){
        this.weightLossGoal = weightLossGoal;
        this.goalCompleted = goalCompleted;
        this.duration = duration;
        this.setClient(client);
        this.setTaskList(taskList);
    }

    public Plan(int weightLossGoal, int goalCompleted, int duration, Client client){
        this(weightLossGoal, goalCompleted, duration, client, null);
    }

    public Plan() {
        this.goalCompleted = 0;// A new plan is not completed by default
        this.duration = 0;// Default to zero if the duration is not yet known
        // Client and Coach would be null by default if not set
    }

    public boolean endPlan(){
        //TODO
        return true;
    }

    public Client getClient() {
        return client;
    }

    public int getWeightLossGoal() {
        return weightLossGoal;
    }

    public int getGoalCompleted() {
        return goalCompleted;
    }

    public int getDuration() {
        return duration;
    }

    public List<DailyTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<DailyTask> taskList) {
        this.taskList = taskList;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
