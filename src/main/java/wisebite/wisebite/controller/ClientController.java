package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wisebite.wisebite.model.DailyTask;
import wisebite.wisebite.service.PlanningService;

import java.util.List;

@RestController
public class ClientController {
    private PlanningService planningService;
    private UserManagementService userManagementService;

    @Autowired
    public ClientController(PlanningService planningService, UserManagementService userManagementService) {
        this.planningService = planningService;
        this.userManagementService = userManagementService;
    }
    @GetMapping("/client/{username}/dailytasklist")
    public ResponseEntity<List<DailyTask>> findByClient(@PathVariable String username){
        List<DailyTask> dailyTask = planningService.findByClient(username);
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
}


