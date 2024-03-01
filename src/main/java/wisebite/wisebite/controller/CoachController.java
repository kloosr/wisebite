package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        Workout workout = planningService.getById(id);
        if (workout == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(workout);
        }
//        return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
