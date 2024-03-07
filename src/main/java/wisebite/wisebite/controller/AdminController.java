package wisebite.wisebite.controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import wisebite.wisebite.model.*;
import wisebite.wisebite.service.AdminService;
import wisebite.wisebite.service.AuthenticationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class AdminController {

    private final AdminService adminService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AdminController(AdminService adminService, AuthenticationService authenticationService) {
        this.adminService = adminService;
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint that allows the registration of new clients
     * @param clientInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/client")
    private ResponseEntity<String> registerClient(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
            , @Valid @RequestBody ClientInfo clientInfo, UriComponentsBuilder ucb) {
        String token = createUsableToken(jwtToken);
        if (hasAdminAccess(token)) {
            String username = adminService.registerUser(clientInfo);
            return createPositiveResponse(username, ucb);
        } else {
            return createNegativeResponse();
        }
    }
    /**
     * Endpoint that allows the registration of new dietitians
     * @param dietitianInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/dietitian")
    private ResponseEntity<String> registerDietitian(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
            , @Valid @RequestBody DietitianInfo dietitianInfo, UriComponentsBuilder ucb) {
        String token = createUsableToken(jwtToken);
        if (hasAdminAccess(token)) {
            String username = adminService.registerUser(dietitianInfo);
            return createPositiveResponse(username, ucb);
        } else {
            return createNegativeResponse();
        }
    }
    /**
     * Endpoint that allows the registration of new coachces
     * @param coachInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/coach")
    private ResponseEntity<String> registerCoach(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
            , @Valid @RequestBody CoachInfo coachInfo, UriComponentsBuilder ucb) {
        String token = createUsableToken(jwtToken);
        if (hasAdminAccess(token)) {
            String username = adminService.registerUser(coachInfo);
            return createPositiveResponse(username, ucb);
        } else {
            return createNegativeResponse();
        }
    }
    /**
     * Endpoint that allows the registration of new admins
     * @param adminInfo RequestBody that gets its attributes checked through the @Valid annotation
     * @param ucb Used to create the ReponseEntity
     * @return
     */
    @PostMapping("/new/admin")
    public ResponseEntity<String> registerUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
            , @Valid @RequestBody AdminInfo adminInfo, UriComponentsBuilder ucb) {
        String token = createUsableToken(jwtToken);
        if (hasAdminAccess(token)) {
            String username = adminService.registerUser(adminInfo);
            return createPositiveResponse(username, ucb);
        } else {
            return createNegativeResponse();
        }
    }

    /**
     * Deletes a user from the database. Provided in the @RequestBody.
     * @param userInfo RequestBody that contains the information needed to delete a user from the database
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
            , @RequestBody UserInfo userInfo) {
        String token = createUsableToken(jwtToken);
        if (hasAdminAccess(token)) {
            return adminService.deleteUser(userInfo);
        } else {
            return createNegativeResponse();
        }
    }
    private boolean hasAdminAccess(String jwtToken) {
        return authenticationService.hasAccess(jwtToken, UserTypeEnum.ADMIN);
    }
    private ResponseEntity<String> createPositiveResponse(String username, UriComponentsBuilder ucb) {
        return ResponseEntity.created(ucb.path("/user/{username}").buildAndExpand(username).toUri()).body("User added.");
    }
    private ResponseEntity<String> createNegativeResponse() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to do this.");
    }
    private String createUsableToken(String jwtToken) {
        return jwtToken.substring(7);
    }
}
