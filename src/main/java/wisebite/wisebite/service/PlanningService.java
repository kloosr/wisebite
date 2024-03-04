package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Workout;
import wisebite.wisebite.repository.DailyTaskRepository;
import wisebite.wisebite.repository.DietRepository;
import wisebite.wisebite.repository.WorkoutRepository;

import java.util.List;

@Service
public class PlanningService {
    private final WorkoutRepository workoutRepository;
    private final DietRepository dietRepository;
    private final DailyTaskRepository dailyTaskRepository;
    @Autowired
    public PlanningService(WorkoutRepository workoutRepository, DietRepository dietRepository, DailyTaskRepository dailyTaskRepository) {
        this.workoutRepository = workoutRepository;
        this.dietRepository = dietRepository;
        this.dailyTaskRepository = dailyTaskRepository;}

    public Workout getWorkoutById(int id) {
        return workoutRepository.createWorkout(id);
    }
    public Diet getDietById(int id) {
        return dietRepository.createDietById(id);
    }

    public List<DailyTask> findByClient(String clientUsername){
        return dailyTaskRepository.findByClient(clientUsername);
    }
}
