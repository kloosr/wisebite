package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import wisebite.wisebite.model.Admin;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.service.AdminService;
import wisebite.wisebite.model.Dietitian;

@RestController
@RequestMapping("/user")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/new/client")
    private ResponseEntity<Void> registerClient(@RequestBody Client client, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(client);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).build();
    }
    @PostMapping("/new/dietitian")
    private ResponseEntity<Void> registerDietitian(@RequestBody Dietitian dietitian, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(dietitian);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).build();
    }
    @PostMapping("/new/coach")
    private ResponseEntity<Void> registerCoach(@RequestBody Coach coach, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(coach);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).build();
    }
    @PostMapping("/new/admin")
    public ResponseEntity<Void> registerUser(@RequestBody Admin admin, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(admin);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).build();
    }
}
