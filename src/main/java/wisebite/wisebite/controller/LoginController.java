package wisebite.wisebite.controller;

import org.springframework.http.ResponseEntity;
import wisebite.wisebite.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.model.UserInfo;
import wisebite.wisebite.service.AuthenticationService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final AuthenticationService authenticationService;
    @Autowired
    public LoginController(AuthenticationService authenticationService) {this.authenticationService = authenticationService;}

    @PostMapping
    public ResponseEntity<?> loginAttempt(@RequestBody UserInfo userInfo) {
        return authenticationService.login(userInfo.getUsername(), userInfo.getPassword());
    }
}
