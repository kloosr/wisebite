package wisebite.wisebite.repository;

import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.ClientDAO;
import wisebite.wisebite.database.CoachDAO;
import wisebite.wisebite.database.DietitianDAO;
import wisebite.wisebite.model.Coach;

import java.util.List;

@Repository
public class CoachRepository {
    private CoachDAO coachDAO;
    public CoachRepository(ClientDAO clientDAO, DietitianDAO dietitianDAO, CoachDAO coachDAO) {
        this.coachDAO = coachDAO;
    }

    public List<Coach> getAllCoaches(){
        return coachDAO.getAllCoaches();
    }

}
