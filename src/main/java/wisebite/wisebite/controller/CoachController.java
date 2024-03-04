package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.database.CoachDAO;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Workout;
import wisebite.wisebite.service.ClientService;
import wisebite.wisebite.service.PlanningService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {

    private final ClientService clientService;
    private final CoachDAO coachDAO;

    @Autowired
    public CoachController(ClientService clientService, CoachDAO coachDAO) {
        this.clientService = clientService;
        this.coachDAO = coachDAO;
    }

    // Endpoint om een overzicht van alle cliënten voor de coach op te halen
    @GetMapping("/overview")
    public ResponseEntity<List<Client>> getClientsOverview(@RequestParam String coachUsername) {
        List<Client> clients = clientService.getClientsForCoachByUsername(coachUsername);
        return ResponseEntity.ok(clients);
    }
}