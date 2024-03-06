package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.ExerciseDAO;
import wisebite.wisebite.database.WorkoutDAO;
import wisebite.wisebite.model.Exercise;
import wisebite.wisebite.model.Workout;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class WorkoutRepository {
    // class attributes for Spring injection
    private final WorkoutDAO workoutDAO;
    private final ExerciseDAO exerciseDAO;

    @Autowired
    public WorkoutRepository(WorkoutDAO workoutDAO, ExerciseDAO exerciseDAO) {
        this.workoutDAO = workoutDAO;
        this.exerciseDAO = exerciseDAO;
    }

    // Get the workout corresponding with given id from the database
    public Workout createWorkout(int id) {
        Workout workout = new Workout();
        // if workout exists
        if (workoutDAO.getWorkoutById(id) != null) {
            // get all workout information
            workout = workoutDAO.getWorkoutById(id);
            // find the list with exercies for this workout and add it to the object
            List<Exercise> exerciseList = exerciseDAO.getAllByWorkout(id);
            workout.setExerciseList(exerciseList);
        } else {
            return null;
        }
        return workout;
    }

    public Workout createWorkoutByDateUsername(Date date, String username) {
        Workout workout = new Workout();
        int id = workoutDAO.getIdByDateAndClient(date, username);
        // if workout exists
        if (id != 0) {
            // get all workout information
            workout = workoutDAO.getWorkoutById(id);
            // find the list with exercies for this workout and add it to the object
            List<Exercise> exerciseList = exerciseDAO.getAllByWorkout(id);
            workout.setExerciseList(exerciseList);
        } else {
            return null;
        }
        return workout;
    }
}

