package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.dto.ClientDTO;
import wisebite.wisebite.dto.MapperClient;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.service.UserManagementService;

import java.util.List;

@RestController
    @RequestMapping("/dietitian")
    public class DietitianController {
    private final UserManagementService userManagementService;
    private MapperClient mapper;


    @Autowired
    public DietitianController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
        this.mapper = new MapperClient();
    }

    @GetMapping("dietitan/overview/{username}")
    public ResponseEntity<List<Client>> getAllClientsOfDietitian(String dietitianUsername) {
        List<Client> clients = userManagementService.getAllClientsOfDietitian(dietitianUsername);
        if (clients != null) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/overview/client/{username}")
    public ResponseEntity<ClientDTO> getSingleClient(@PathVariable String username) {
        Client client = userManagementService.getSingleClient(username);
        if (client != null) {
            ClientDTO clientDTO = mapper.toDto(client);
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("dietitian/overview/client")
    public ResponseEntity<Client> getClientInformation(@RequestParam("username") String username) {
        Client client = userManagementService.getSingleClient(username);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userManagementService.isClientOnDietitianList(username)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
    @GetMapping("dietitian/overview/coaches")
    public List<Coach> getAllCoaches() {
        return userManagementService.getAllCoaches();
    }
    @GetMapping("/sorted-by-bmi")
    public ResponseEntity<List<Client>> getClientsSortedByBmiAndDietitian(String dietitianUsername) {
        List<Client> clients = userManagementService.getAllClientsOfDietitian(dietitianUsername);
        return (ResponseEntity<List<Client>>) userManagementService.sortClientsByBMI(clients);
    }
}



