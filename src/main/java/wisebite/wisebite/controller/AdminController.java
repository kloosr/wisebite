package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import wisebite.wisebite.dto.AdminDTO;
import wisebite.wisebite.dto.ClientDTO;
import wisebite.wisebite.dto.CoachDTO;
import wisebite.wisebite.dto.DietitianDTO;
import wisebite.wisebite.model.*;
import wisebite.wisebite.service.AdminService;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/new/client")
    @Validated
    private ResponseEntity<String> registerClient(@Valid @RequestBody ClientDTO clientDTO, UriComponentsBuilder ucb) {
        Client client = clientDTO.convertToClient();
        if (usernameExists(client)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(client);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/new/dietitian")
    private ResponseEntity<String> registerDietitian(@Valid @RequestBody DietitianDTO dietitianDTO, UriComponentsBuilder ucb) {
        Dietitian dietitian = dietitianDTO.convertToDietitian();
        if (usernameExists(dietitian)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(dietitian);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/new/coach")
    private ResponseEntity<String> registerCoach(@Valid @RequestBody CoachDTO coachDTO, UriComponentsBuilder ucb) {
        Coach coach = coachDTO.convertToCoach();
        if (usernameExists(coach)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(coach);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/new/admin")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AdminDTO adminDTO, UriComponentsBuilder ucb) {
        Admin admin = adminDTO.convertToAdmin();
        if (usernameExists(admin)) {
            return ResponseEntity.status(409).body("Username already exists.");
        } else {
            String username = adminService.registerUser(admin);
            return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
        }
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        if (usernameExists(user)) {
            adminService.deleteUser(user);
            return ResponseEntity.status(200).body("User successfully deleted.");
        } else {
            return ResponseEntity.status(404).body("User does not exist.");
        }
    }
    private boolean usernameExists(User user) {
        return adminService.usernameExists(user);
    }
}
