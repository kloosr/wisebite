package wisebite.wisebite.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Dietitian;
import wisebite.wisebite.model.User;
import wisebite.wisebite.service.UserManagementService;

import java.util.List;

@RestController
    @RequestMapping("/dietitian")
    public class DietitianController {
    private final UserManagementService userManagementService;

    @Autowired
    public DietitianController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/overview/{username}")
    public ResponseEntity<List<Client>> getDietitianOverview(String dietitianUsername) {
        List<Client> clients = userManagementService.getClientsForDietitian(dietitianUsername);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @GetMapping("/client/{username}")
    public ResponseEntity<Client> getClientByUsername(@PathVariable String username) {
        Client client = userManagementService.findClientByUsername(username);
        if (client != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



