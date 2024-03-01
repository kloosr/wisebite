package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.repository.DietitianRepository;

import java.util.List;

@Service
public class UserManagementService {
    private DietitianRepository dietitianRepository;
    @Autowired
    public UserManagementService(DietitianRepository dietitianRepository) {
        this.dietitianRepository = dietitianRepository;
    }

    public List<Client> getClientsForDietitian(String dietitianUsername) {
        return dietitianRepository.findAllClientsByDietitian(dietitianUsername);
    }
    public Client findClientByUsername(String username) {
        return dietitianRepository.getSingleClient(username);
    }
}

