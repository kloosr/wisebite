package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.ClientDAO;
import wisebite.wisebite.database.DailyTaskDAO;
import wisebite.wisebite.database.DietDAO;
import wisebite.wisebite.database.WorkoutDAO;
import wisebite.wisebite.model.DailyTask;

import java.util.List;

@Repository
public class DailyTaskRepository {
    private final DailyTaskDAO dailyTaskDAO;
    private final ClientDAO clientDAO;
    private final WorkoutRepository workoutRepository;
    private final DietRepository dietRepository;
    private final WorkoutDAO workoutDAO;
    private final DietDAO dietDAO;

    @Autowired
    public DailyTaskRepository(DailyTaskDAO dailyTaskDAO, ClientDAO clientDAO, WorkoutRepository workoutRepository,
                               DietRepository dietRepository, WorkoutDAO workoutDAO, DietDAO dietDAO) {
        this.dailyTaskDAO = dailyTaskDAO;
        this.clientDAO = clientDAO;
        this.workoutRepository = workoutRepository;
        this.dietRepository = dietRepository;
        this.workoutDAO = workoutDAO;
        this.dietDAO = dietDAO;
    }

    public List<DailyTask> getDailyTaskForClient (String username) {
        List<DailyTask> taskList = dailyTaskDAO.getDailyTaskForClient(username);
        for (DailyTask task : taskList) {
            task.setClient(clientDAO.findClientByUsername(username));
            task.setWorkout(workoutRepository.createWorkoutByDateUsername(task.getDate(), username));
            task.setDiet(dietRepository.createDietByDateUsername(task.getDate(), username));
        }
        return taskList;
    }

    public void storeDailyTask(DailyTask dailyTask){
        dailyTaskDAO.storeDailyTask(dailyTask);
    }
}

