package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.*;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.User;

import java.util.List;

@Repository
public class ClientRepository {
    private ClientDAO clientDAO;
    public ClientRepository(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public boolean isClientOnDietitianList(String username){
        return clientDAO.isClientOnDietitianList(username);
    }

}
