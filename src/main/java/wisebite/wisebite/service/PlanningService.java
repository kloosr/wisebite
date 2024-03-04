package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Workout;
import wisebite.wisebite.repository.DietRepository;
import wisebite.wisebite.repository.WorkoutRepository;

@Service
public class PlanningService {
    private final WorkoutRepository workoutRepository;
    private final DietRepository dietRepository;
    @Autowired
    public PlanningService(WorkoutRepository workoutRepository, DietRepository dietRepository) {this.workoutRepository = workoutRepository; this.dietRepository = dietRepository;}

    public Workout getWorkoutById(int id) {
        return workoutRepository.createWorkout(id);
    }
    public Diet getDietById(int id) {return dietRepository.createDietById(id);}
}
