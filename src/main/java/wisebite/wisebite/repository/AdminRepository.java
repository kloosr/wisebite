package wisebite.wisebite.repository;

import wisebite.wisebite.database.*;
import wisebite.wisebite.model.*;
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
    public void createUser(User user) {
        UserTypeEnum userType = user.getUserType();
        userDAO.storeUser(user);
        switch (userType) {
            case ADMIN:
                adminDAO.storeAdmin((Admin) user);
                break;
            case CLIENT:
                clientDAO.storeClient((Client) user);
                break;
            case DIETITIAN:
                dietitianDAO.storeDietitian((Dietitian) user);
                break;
            case COACH:
                coachDAO.storeCoach((Coach) user);
                break;
            default:
                break;
        }
    }
}
