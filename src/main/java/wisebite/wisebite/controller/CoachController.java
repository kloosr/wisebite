package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.model.Diet;
import wisebite.wisebite.model.Plan;
import wisebite.wisebite.model.Workout;
import wisebite.wisebite.service.PlanningService;

import java.util.Optional;

@RestController
@RequestMapping("/coach")
public class CoachController {
    private final PlanningService planningService;
    @Autowired
    public CoachController(PlanningService planningService) {this.planningService = planningService;}
    @GetMapping("/workout/{id}")
    private ResponseEntity<Workout> getById(@PathVariable int id) {
        Workout workout = planningService.getWorkoutById(id);
        if (workout == null) {
            // TODO figure out how to sent error message
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(workout);
        }
    }
    @GetMapping("/diet/{id}")
    private ResponseEntity<Diet> getDietById(@PathVariable int id) {
        Diet diet = planningService.getDietById(id);
        if (diet == null) {
            // TODO figure out how to sent error message
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(diet);
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
