package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import service.AdminService;

@RestController
@RequestMapping("/users/")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/new/client/")
    private ResponseEntity<Void> registerClient(@RequestBody Client client, UriComponentsBuilder ucb) {
        String username = adminService.registerClient(client);
        return ResponseEntity.created(ucb.path("users/username={username}").buildAndExpand(username).toUri()).build();
    }
    @PostMapping("/new/dietitian/")
    private ResponseEntity<Void> registerDietitian(@RequestBody Dietitian dietitian, UriComponentsBuilder ucb) {
        String username = adminService.registerDietitian(dietitian);
        return ResponseEntity.created(ucb.path("users/username={username}").buildAndExpand(username).toUri()).build();
    }
    @PostMapping("/new/coach/")
    private ResponseEntity<Void> registerCoach(@RequestBody Coach coach, UriComponentsBuilder ucb) {
        String username = adminService.registerCoach(coach);
        return ResponseEntity.created(ucb.path("users/username={username}").buildAndExpand(username).toUri()).build();
    }
    @PostMapping("/new/admin/")
    private ResponseEntity<Void> registerUser(@RequestBody Admin admin, UriComponentsBuilder ucb) {
        String username = adminService.registerAdmin(admin);
        return ResponseEntity.created(ucb.path("users/username={username}").buildAndExpand(username).toUri()).build();
    }
}
