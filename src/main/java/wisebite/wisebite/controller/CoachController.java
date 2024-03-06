package wisebite.wisebite.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.model.Plan;
import wisebite.wisebite.service.PlanningService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.service.UserManagementService;

import java.util.List;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final UserManagementService userManagementService;
    private final PlanningService planningService;

    @Autowired
    public CoachController(PlanningService planningService, UserManagementService userManagementService) {
        this.planningService = planningService;
        this.userManagementService = userManagementService;
    }

    // Endpoint om een overzicht van alle cliënten voor de coach op te halen
    @GetMapping("/overview")
    public ResponseEntity<?> getClientsOverview(@RequestParam String coachUsername) {

        //  public ResponseEntity<?> getClientsOverview(@RequestParam String coachUsername) {
        List<Client> clients = userManagementService.findAllByCoach(coachUsername);
        if (coachUsername == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(clients);
            // if token != authorized return 401
        }
    }

    @GetMapping("/plan/{username}")
    private ResponseEntity<Plan> getPlanByClient(@PathVariable String username) {
        Plan plan = planningService.getPlanByClient(username);
        if (plan == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(plan);
        }
    }


}

