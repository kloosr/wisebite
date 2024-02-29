package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import wisebite.wisebite.model.*;
import wisebite.wisebite.service.AdminService;

@RestController
@RequestMapping("/user")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/new/client")
    private ResponseEntity<String> registerClient(@RequestBody Client client, UriComponentsBuilder ucb) {
        if (usernameExists(client)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(client);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/new/dietitian")
    private ResponseEntity<String> registerDietitian(@RequestBody Dietitian dietitian, UriComponentsBuilder ucb) {
        if (usernameExists(dietitian)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(dietitian);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/new/coach")
    private ResponseEntity<String> registerCoach(@RequestBody Coach coach, UriComponentsBuilder ucb) {
        if (usernameExists(coach)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(coach);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/new/admin")
    public ResponseEntity<String> registerUser(@RequestBody Admin admin, UriComponentsBuilder ucb) {
        if (usernameExists(admin)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(admin);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    private boolean usernameExists(User user) {
        return adminService.usernameExists(user);
    }
}
