package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.*;

@Repository
public class ClientRepository {
    private ClientDAO clientDAO;
    private DietitianDAO dietitianDAO;
    @Autowired
    public ClientRepository(ClientDAO clientDAO, DietitianDAO dietitianDAO) {
        this.clientDAO = clientDAO;
        this.dietitianDAO = dietitianDAO;
    }

    public boolean isClientOnDietitianList(String username) {
        return clientDAO.isClientOnDietitianList(username);
    }
    public Double getWeight(String username) {
        return clientDAO.getWeight(username);
    }
    public Double getHeight(String username) {
        return clientDAO.getHeight(username);
    }
}

