package wisebite.wisebite.repository;

import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.ClientDAO;
import wisebite.wisebite.database.CoachDAO;
import wisebite.wisebite.database.DietitianDAO;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;

import java.util.List;

@Repository
public class DietitianRepository {
    private ClientDAO clientDAO;
    private DietitianDAO dietitianDAO;
    private CoachDAO coachDAO;

    public DietitianRepository(ClientDAO clientDAO, DietitianDAO dietitianDAO, CoachDAO coachDAO) {
        this.clientDAO = clientDAO;
        this.dietitianDAO = dietitianDAO;
        this.coachDAO = coachDAO;
    }

    public List<Client> findAllClientsByDietitian(String dietitianUsername) {
        return clientDAO.findClientsByDietitian(dietitianUsername);
    }

    public Client getSingleClient(String username) {
        return clientDAO.findByUsername(username);
    }

}