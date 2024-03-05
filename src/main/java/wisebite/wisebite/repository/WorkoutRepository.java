package wisebite.wisebite.repository;

import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.ExerciseDAO;
import wisebite.wisebite.database.WorkoutDAO;
import wisebite.wisebite.model.Exercise;
import wisebite.wisebite.model.Workout;

import java.util.List;
import java.util.Optional;

@Repository
public class WorkoutRepository {
    private final WorkoutDAO workoutDAO;
    private final ExerciseDAO exerciseDAO;
    public WorkoutRepository(WorkoutDAO workoutDAO, ExerciseDAO exerciseDAO){
        this.workoutDAO = workoutDAO;
        this.exerciseDAO = exerciseDAO;
    }
    public Workout createWorkout(int id){
        Workout workout = new Workout();
        if (workoutDAO.getWorkoutById(id) != null) {
            workout = workoutDAO.getWorkoutById(id);
            List<Exercise> exerciseList = exerciseDAO.getAllByWorkout(id);
            workout.setExerciseList(exerciseList);
        } else {
            return null;
        }
        return workout;
    }
}
