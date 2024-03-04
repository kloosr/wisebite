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
@RestController
@RequestMapping
public class ClientController {
    private PlanningService planningService;

    @Autowired
    public ClientController(PlanningService planningService) {
        this.planningService = planningService;
    }
    @GetMapping("/client/{username}/dailytasklist")
    public ResponseEntity<DailyTask> findByClient(@PathVariable String clientUsername){
        DailyTask dailyTask = planningService.findByClient(clientUsername);
        if (dailyTask != null){
            return new ResponseEntity<>(dailyTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


