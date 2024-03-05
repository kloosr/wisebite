package wisebite.wisebite.controller;

import wisebite.wisebite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.service.AuthenticationService;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationService authenticationService;
    @Autowired
    public LoginController(AuthenticationService authenticationService) {this.authenticationService = authenticationService;}

//    @GetMapping()
//    public Optional<User> findByUserName (@RequestBody String username, String password) {
//        return authenticationService.findByUsername(username);
//    }

}
