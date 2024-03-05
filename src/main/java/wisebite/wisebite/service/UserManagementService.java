package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.repository.ClientRepository;
import wisebite.wisebite.repository.CoachRepository;
import wisebite.wisebite.repository.DietitianRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserManagementService {
    private DietitianRepository dietitianRepository;
    private ClientRepository clientRepository;
    private CoachRepository coachRepository;
    Client client;

    @Autowired
    public UserManagementService(DietitianRepository dietitianRepository, ClientRepository clientRepository) {
        this.dietitianRepository = dietitianRepository;
        this.clientRepository = clientRepository;
        this.coachRepository = coachRepository;
    }

    public List<Client> getAllClientsOfDietitian(String dietitianUsername) {
        return dietitianRepository.findAllClientsByDietitian(dietitianUsername);
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.getAllCoaches();
    }
    public List<Client> findAllByCoach(String coachUsername){
        return clientRepository.getAllClients(coachUsername);
    }

    public Client getSingleClient(String username) {
        return dietitianRepository.getSingleClient(username);
    }

    public boolean isClientOnDietitianList(String username) {
        return clientRepository.isClientOnDietitianList(username);
    }

    public double calculateClientBMI(Client client) {
        double weight = clientRepository.getWeight(client.getUsername());
        double height = clientRepository.getHeight(client.getUsername()) / 100;
        double bmiOfClient = weight / (height * height);
        return bmiOfClient;
    }
    public List<Client> sortClientsByBMI(List<Client> clients) {
        Collections.sort(clients, Comparator.comparingDouble(this::calculateClientBMI));
        return clients;
    }

    public String checkBmiCategory() {
        double bmiOfClient = calculateClientBMI(client);
        if (bmiOfClient < 18.5) {
            return "Underweight";
        } else if (bmiOfClient >= 18.5 && bmiOfClient < 25) {
            return "Normal weight";
        } else if (bmiOfClient >= 25 && bmiOfClient < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
}