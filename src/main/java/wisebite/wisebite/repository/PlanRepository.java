package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.DailyTaskDAO;
import wisebite.wisebite.database.PlanDAO;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.model.Plan;

import java.util.List;

@Repository
public class PlanRepository {
    private final PlanDAO planDAO;
    private final DailyTaskRepository dailyTaskRepository;
    private final DailyTaskDAO dailyTaskDAO;
    @Autowired
    public PlanRepository(DailyTaskRepository dailyTaskRepository, PlanDAO planDAO, DailyTaskDAO dailyTaskDAO) {
        this.dailyTaskRepository = dailyTaskRepository;
        this.planDAO = planDAO;
        this.dailyTaskDAO = dailyTaskDAO;
    }
    public Plan getPlanByClient (String username) {
        Plan plan = planDAO.getByClient(username);
        List<DailyTask> taskList = dailyTaskRepository.findByClient(username);
        plan.setTaskList(taskList);
        return plan;
    }
}
