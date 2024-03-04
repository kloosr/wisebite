package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.DailyTaskDAO;
import wisebite.wisebite.dto.DailyTaskDTO;
import wisebite.wisebite.model.DailyTask;

import java.util.Date;
import java.util.List;
@Repository
public class DailyTaskRepository {
    private DailyTaskDAO dailyTaskDAO;
    @Autowired
public DailyTaskRepository(DailyTaskDAO dailyTaskDAO){
    this.dailyTaskDAO = dailyTaskDAO;
}
    public List<DailyTask> findByClient(String clientUsername){
        return dailyTaskDAO.findByClient(clientUsername);
    }
}

