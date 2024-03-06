package wisebite.wisebite.dto;

import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class PlanDTO {
    private Client client;
    private int weightLossGoal;
    private int duration;
    private int goalCompleted;
    private List<DailyTaskDTO> dtoTaskList;

    public PlanDTO(Client client, int weightLossGoal, int duration, int goalCompleted, List<DailyTaskDTO> dtoTaskList) {
        this.client = client;
        this.weightLossGoal = weightLossGoal;
        this.duration = duration;
        this.goalCompleted = goalCompleted;
        this.dtoTaskList = dtoTaskList;
    }

    public static PlanDTO convertToDTO (Plan plan) {
        List<DailyTaskDTO> dtoList = new ArrayList<>();
        for (DailyTask task : plan.getTaskList()) {
            dtoList.add(new DailyTaskDTO(task));
        }
        return new PlanDTO(plan.getClient(), plan.getWeightLossGoal(),
                plan.getDuration(), plan.getGoalCompleted(),
                dtoList);
    }

    public Client getClient() {
        return client;
    }

    public int getWeightLossGoal() {
        return weightLossGoal;
    }

    public int getDuration() {
        return duration;
    }

    public int getGoalCompleted() {
        return goalCompleted;
    }

    public List<DailyTaskDTO> getDtoTaskList() {
        return dtoTaskList;
    }
}
