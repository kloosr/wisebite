package wisebite.wisebite.repository;

import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.DailyTaskDAO;
import wisebite.wisebite.model.DailyTask;

import java.util.Date;
import java.util.List;
@Repository
public class DailyTaskRepository {
    public List<DailyTask> findByClientAndDate (String clientUsername, Date date){
        return DailyTaskDAO.findByClientAndDate(clientUsername, date);
    }
}
