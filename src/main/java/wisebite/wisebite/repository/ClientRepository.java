package wisebite.wisebite.repository;

import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.ClientDAO;
import wisebite.wisebite.model.Client;

import java.util.List;
@Repository
public class ClientRepository {
    private ClientDAO clientDAO;
    public ClientRepository(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    public List<Client> findAllClientsByDietitian(String dietitianUsername) {
        return clientDAO.findClientByDietitian(dietitianUsername);
    }

    public Client getSingleClient(String username) {
        return clientDAO.findClientByUsername(username);
    }
}
