package wisebite.wisebite.repository;


import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.*;
import wisebite.wisebite.model.Client;


import java.util.List;

@Repository
public class ClientRepository {
        private ClientDAO clientDAO;
        private DietitianDAO dietitianDAO;
        public ClientRepository(ClientDAO clientDAO, DietitianDAO dietitianDAO) {
            this.clientDAO = clientDAO;
            this.dietitianDAO = dietitianDAO;
        }

    public List<Client> getAllClients(String username) {
        return clientDAO.findClientByCoach(username);

    }

    public boolean isClientOnDietitianList(String username) {
        return clientDAO.isClientOnDietitianList(username);
    }

}
