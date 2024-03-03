package wisebite.wisebite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.DailyTaskDAO;
import wisebite.wisebite.model.DailyTask;

import java.util.Date;
import java.util.List;
@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Integer> {
    List<DailyTask> findByClientAndDate (String clientUsername, Date date);
    }

