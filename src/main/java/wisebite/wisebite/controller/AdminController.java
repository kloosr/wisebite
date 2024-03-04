package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import wisebite.wisebite.dto.*;
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
    private ResponseEntity<String> registerClient(@Valid @RequestBody ClientDTO clientDTO, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(clientDTO);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/new/dietitian")
    private ResponseEntity<String> registerDietitian(@Valid @RequestBody DietitianDTO dietitianDTO, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(dietitianDTO);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/new/coach")
    private ResponseEntity<String> registerCoach(@Valid @RequestBody CoachDTO coachDTO, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(coachDTO);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/new/admin")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AdminDTO adminDTO, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(adminDTO);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDTO) {
        return adminService.deleteUser(userDTO);
    }
}
