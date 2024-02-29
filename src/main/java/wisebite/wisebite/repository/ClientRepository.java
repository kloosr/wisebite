package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.*;
import wisebite.wisebite.model.Client;
@Repository
public class ClientRepository {
    private final UserDAO userDAO;
    private final ClientDAO clientDAO;
    private final DietitianDAO dietitianDAO;
    private final CoachDAO coachDAO;
    private final AdminDAO adminDAO;

    @Autowired
    public ClientRepository(UserDAO userDAO, ClientDAO clientDAO, DietitianDAO dietitianDAO, CoachDAO coachDAO, AdminDAO adminDAO) {
        this.userDAO = userDAO;
        this.clientDAO = clientDAO;
        this.dietitianDAO = dietitianDAO;
        this.coachDAO = coachDAO;
        this.adminDAO = adminDAO;
    }
    public Client findByUsername(String username) {
        return userDAO.findByUsername(username).isPresent();
    }
}
