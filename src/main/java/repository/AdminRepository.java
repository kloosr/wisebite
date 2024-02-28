package repository;

import database.ClientDAO;
import database.CoachDAO;
import database.DietitianDAO;
import database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    private final UserDAO userDAO;
    private final ClientDAO clientDAO;
    private final DietitianDAO dietitianDAO;
    private final CoachDAO coachDAO;
    @Autowired
    public AdminRepository(UserDAO userDAO, ClientDAO clientDAO, DietitianDAO dietitianDAO, CoachDAO coachDAO){
        this.userDAO = userDAO;
        this.clientDAO = clientDAO;
        this.dietitianDAO = dietitianDAO;
        this.coachDAO = coachDAO;
    }
}
