package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.repository.ClientRepository;
import wisebite.wisebite.service.PlanningService;
import wisebite.wisebite.service.UserManagementService;

import java.util.List;

@RestController
public class ClientController {
    private PlanningService planningService;
    private UserManagementService userManagementService;
    private ClientRepository clientRepository;

    @Autowired
    public ClientController(PlanningService planningService, UserManagementService userManagementService) {
        this.planningService = planningService;
        this.userManagementService = userManagementService;
    }

    @GetMapping("/client/{username}/dailytasklist")
    public ResponseEntity<List<DailyTask>> getDailyTaskForClient(@PathVariable String username){//rename getDailyTask
        List<DailyTask> dailyTask = planningService.getDailyTaskForClient(username);
        if (!dailyTask.isEmpty()){
            return new ResponseEntity<>(dailyTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clients/{username}/dietitian-list")
    public boolean isClientOnDietitianList(@PathVariable String username) {
        return userManagementService.isClientOnDietitianList(username);
    }

    @GetMapping("/{username}/bmi")
    public double getClientBMI(@PathVariable String username) {
        Client client = userManagementService.getSingleClient(username);
        return userManagementService.calculateClientBMI(client);
    }

    @GetMapping("/bmi-category")
    public String checkBmiCategory(@RequestParam double weight, @RequestParam double height) {
        String bmiCategory = userManagementService.checkBmiCategory();
        return bmiCategory;
    }
}


