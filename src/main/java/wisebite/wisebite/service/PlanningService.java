package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.Workout;
import wisebite.wisebite.repository.WorkoutRepository;

@Service
public class PlanningService {
    private final WorkoutRepository workoutRepository;
    @Autowired
    public PlanningService(WorkoutRepository workoutRepository) {this.workoutRepository = workoutRepository;}

    public Workout getById(int id) {
        return workoutRepository.createWorkout(id);
    }
}
