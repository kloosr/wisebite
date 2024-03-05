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
    private final DailyTaskDAO dailyTaskDAO;
    private final PlanDAO planDAO;
    @Autowired
    public PlanRepository(DailyTaskDAO dailyTaskDAO, PlanDAO planDAO) {
        this.dailyTaskDAO = dailyTaskDAO;
        this.planDAO = planDAO;
    }
    public Plan getPlanByClient (String username) {
        Plan plan = planDAO.getByClient(username);
        List<DailyTask> taskList = dailyTaskDAO.findByClientWithoutClientInfo(username);
        plan.setTaskList(taskList);
        return plan;
    }
}
