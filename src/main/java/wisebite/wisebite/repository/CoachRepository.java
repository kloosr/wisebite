package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import wisebite.wisebite.database.ClientDAO;
import wisebite.wisebite.database.CoachDAO;
import org.springframework.stereotype.Repository;
@Repository
public class CoachRepository {
    private CoachDAO coachDAO;
    @Autowired
    public CoachRepository(CoachDAO coachDAO) {
        this.coachDAO = coachDAO;
    }

}
