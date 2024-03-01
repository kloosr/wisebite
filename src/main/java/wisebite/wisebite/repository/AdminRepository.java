package wisebite.wisebite.repository;

import wisebite.wisebite.database.*;
import wisebite.wisebite.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    private final UserDAO userDAO;
    private final ClientDAO clientDAO;
    private final DietitianDAO dietitianDAO;
    private final CoachDAO coachDAO;
    private final AdminDAO adminDAO;
    @Autowired
    public AdminRepository(UserDAO userDAO, ClientDAO clientDAO, DietitianDAO dietitianDAO, CoachDAO coachDAO, AdminDAO adminDAO){
        this.userDAO = userDAO;
        this.clientDAO = clientDAO;
        this.dietitianDAO = dietitianDAO;
        this.coachDAO = coachDAO;
        this.adminDAO = adminDAO;
    }
    public boolean usernameExists(String username) {
        return userDAO.findByUsername(username).isPresent();
    }
    public void createAdmin(Admin admin) {
        adminDAO.storeAdmin(admin);
        userDAO.storeUser(admin);
    }
}