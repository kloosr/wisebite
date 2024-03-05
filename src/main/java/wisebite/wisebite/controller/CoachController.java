package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wisebite.wisebite.database.CoachDAO;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.service.UserManagementService;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {
 private UserManagementService userManagementService;
 private final CoachDAO coachDAO;

    @Autowired
    public CoachController(CoachDAO coachDAO, UserManagementService userManagementService) {
        this.coachDAO = coachDAO;
        this.userManagementService = userManagementService;
    }

    // Endpoint om een overzicht van alle cliënten voor de coach op te halen
    @GetMapping("/overview")
    public ResponseEntity<List<Client>> getClientsOverview(@RequestParam String coachUsername) {
        List<Client> clients = userManagementService.findAllByCoach(coachUsername);
        return ResponseEntity.ok(clients);
    }
}