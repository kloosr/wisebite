package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.dto.DailyTaskDTO;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Plan;
import wisebite.wisebite.model.Workout;
import wisebite.wisebite.repository.DailyTaskRepository;
import wisebite.wisebite.repository.DietRepository;
import wisebite.wisebite.repository.PlanRepository;
import wisebite.wisebite.repository.WorkoutRepository;

import java.util.List;

@Service
public class PlanningService {
    private final WorkoutRepository workoutRepository;
    private final DietRepository dietRepository;
    private final DailyTaskRepository dailyTaskRepository;
    private final PlanRepository planRepository;
    @Autowired
    public PlanningService(WorkoutRepository workoutRepository, DietRepository dietRepository, DailyTaskRepository dailyTaskRepository,
    PlanRepository planRepository) {
        this.workoutRepository = workoutRepository;
        this.dietRepository = dietRepository;
        this.dailyTaskRepository = dailyTaskRepository;
        this.planRepository = planRepository;}

    public Workout getWorkoutById(int id) {
        return workoutRepository.createWorkout(id);
    }
    public Diet getDietById(int id) {
        return dietRepository.createDietById(id);
    }

    public List<DailyTask> getDailyTaskForClient(String clientUsername){
        return dailyTaskRepository.getDailyTaskForClient(clientUsername);
    }
    public Plan getPlanByClient (String username) {
        return planRepository.getPlanByClient(username);
    }
}
