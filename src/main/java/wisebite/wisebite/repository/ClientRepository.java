package wisebite.wisebite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wisebite.wisebite.database.*;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.User;

@Repository
public class ClientRepository {
        private ClientDAO clientDAO;
        private DietitianDAO dietitianDAO;
        public ClientRepository(ClientDAO clientDAO, DietitianDAO dietitianDAO) {
            this.clientDAO = clientDAO;
            this.dietitianDAO = dietitianDAO;
        }

        public boolean isClientOnDietitianList(String username){
            return clientDAO.isClientOnDietitianList(username);
        }

}
