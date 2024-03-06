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

    /**
     * Endpoint that allows the registration of new clients
     * @param clientInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/client")
    private ResponseEntity<String> registerClient(@Valid @RequestBody ClientInfo clientInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(clientInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    /**
     * Endpoint that allows the registration of new dietitians
     * @param dietitianInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/dietitian")
    private ResponseEntity<String> registerDietitian(@Valid @RequestBody DietitianInfo dietitianInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(dietitianInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    /**
     * Endpoint that allows the registration of new coachces
     * @param coachInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/coach")
    private ResponseEntity<String> registerCoach(@Valid @RequestBody CoachInfo coachInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(coachInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    /**
     * Endpoint that allows the registration of new admins
     * @param adminInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/admin")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AdminInfo adminInfo, UriComponentsBuilder ucb) {
        String username = adminService.registerUser(adminInfo);
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }

    /**
     * Deletes a user from the database. Provided in the @RequestBody.
     * @param userInfo RequestBody that contains the information needed to delete a user from the database
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserInfo userInfo) {
        return adminService.deleteUser(userInfo);
    }
}
