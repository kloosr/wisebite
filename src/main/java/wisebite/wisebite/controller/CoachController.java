package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.dto.DailyTaskDTO;
import wisebite.wisebite.dto.PlanDTO;
import wisebite.wisebite.model.DailyTask;
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
    public ResponseEntity<List<Client>> getClientsOverview(@RequestParam String coachUsername) {
        List<Client> clients = userManagementService.findAllByCoach(coachUsername);
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/plan/{username}")
    private ResponseEntity<PlanDTO> getPlanByClient(@PathVariable String username) {
        Plan plan = planningService.getPlanByClient(username);
        if (plan == null) {
            return ResponseEntity.notFound().build();
        } else {
            PlanDTO planDTO = PlanDTO.convertToDTO( plan );
            return ResponseEntity.ok(planDTO);
        }
    }

    @PostMapping("/overview/{username}/plan/add-dailytask")
    private ResponseEntity<?> storeDailyTask (@PathVariable String username, @RequestBody DailyTaskDTO dailyTaskDTO) {
        DailyTask dailyTask = DailyTaskDTO.convertFromDTO(dailyTaskDTO);
        dailyTask.setClient(userManagementService.findClientByUsername(username));
        planningService.storeDailyTask(dailyTask);
        return ResponseEntity.ok().body("Daily task added successfully");
    }

}
