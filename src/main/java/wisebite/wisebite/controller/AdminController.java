package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
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
    private ResponseEntity<String> registerClient(@Valid @RequestBody ClientInfo clientInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(clientInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/new/dietitian")
    private ResponseEntity<String> registerDietitian(@Valid @RequestBody DietitianInfo dietitianInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(dietitianInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/new/coach")
    private ResponseEntity<String> registerCoach(@Valid @RequestBody CoachInfo coachInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(coachInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/new/admin")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AdminInfo adminInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(adminInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserInfo userInfo) {
        return adminService.deleteUser(userInfo);
    }
}
