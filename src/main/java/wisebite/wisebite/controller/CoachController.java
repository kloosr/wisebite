package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.dto.PlanDTO;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.model.Plan;
import wisebite.wisebite.model.UserTypeEnum;
import wisebite.wisebite.service.AuthenticationService;
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
    private final AuthenticationService authenticationService;


    @Autowired
    public CoachController(PlanningService planningService, UserManagementService userManagementService, AuthenticationService authenticationService) {
        this.planningService = planningService;
        this.userManagementService = userManagementService;
        this.authenticationService = authenticationService;
    }

    // Endpoint om een overzicht van alle cliënten voor de coach op te halen
    @GetMapping("/overview")
    public ResponseEntity<List<Client>> getClientsOverview(@RequestParam String coachUsername) {
        List<Client> clients = userManagementService.findAllByCoach(coachUsername);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/overview/{username}")
    private ResponseEntity<?> getPlanByClient(@PathVariable String username, @RequestHeader String jwtToken) {
        if (userManagementService.clientExists(username)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND + " The page you are looking for can not be found");
        } else if (!userManagementService.clientIsOnCoachList(username, authenticationService.getUsername(jwtToken)) || !hasAccess(jwtToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpStatus.FORBIDDEN + " You don't have access to see this patient");
        } else {
            Plan plan = planningService.getPlanByClient(username);
            if (plan == null) {
                return ResponseEntity.notFound().build();
            } else {
                PlanDTO planDTO = PlanDTO.convertToDTO(plan);
                return ResponseEntity.ok(planDTO);
            }
        }
    }

    private boolean hasAccess(String jwtToken) {
        return authenticationService.hasAccess(jwtToken, UserTypeEnum.COACH);
    }
}

