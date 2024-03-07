package wisebite.wisebite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping
    public ResponseEntity<?> login (@RequestHeader String username, @RequestHeader String password) {
        if (authenticationService.checkPassword(password, username)) {
            return ResponseEntity.ok(authenticationService.getToken(username));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
