package wisebite.wisebite.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.repository.ClientRepository;
import wisebite.wisebite.repository.CoachRepository;
import wisebite.wisebite.repository.DietitianRepository;

import java.util.List;

@Service
public class UserManagementService {
    private DietitianRepository dietitianRepository;
    private ClientRepository clientRepository;
    private CoachRepository coachRepository;
    private AuthenticationService authenticationService;
    @Autowired
    public UserManagementService(DietitianRepository dietitianRepository, ClientRepository clientRepository, CoachRepository coachRepository) {
        this.clientRepository = clientRepository;
        this.dietitianRepository = dietitianRepository;
        this.coachRepository = coachRepository;
    }

    public List<Client> getClientsForDietitian(String dietitianUsername) {
        return dietitianRepository.findAllClientsByDietitian(dietitianUsername);
    }
    public List<Client> findAllByCoach(String coachUsername){
       return clientRepository.getAllClients(coachUsername);
    }

    public List<Coach> getAllCoaches(){
        return coachRepository.getAllCoaches();
    }
    public Client findClientByUsername(String username) {
        return dietitianRepository.getSingleClient(username);
    }

    public boolean isClientOnDietitianList(String username){
        return clientRepository.isClientOnDietitianList(username);
    }

    public boolean clientIsOnCoachList (String client, String coach) {
        return clientRepository.isClientOnCoachList(client, coach);
    }
    public boolean clientExists (String client) {
        return clientRepository.clientExists(client);
    }
}