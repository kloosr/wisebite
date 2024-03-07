package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
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
    public ResponseEntity<List<Client>> getDietitianOverview(@PathVariable String username) {
        List<Client> clients = userManagementService.getClientsForDietitian(username);
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

    @GetMapping("/coaches")
    public List<Coach> getAllCoaches() {
        return userManagementService.getAllCoaches();
    }

    @PostMapping("/dietitian/overview/{username}")
    public ResponseEntity<?> assignCoachToClient(@PathVariable @RequestBody String username, @RequestBody String coach, UriComponentsBuilder ucb) {
        if (coach == null || coach.isEmpty() || username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Coach username and client username are required.");
        } else {
            return ResponseEntity.ok("Coach assigned to client successfully.");
        }
    }
}



