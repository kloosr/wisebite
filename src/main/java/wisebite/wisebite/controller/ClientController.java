package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wisebite.wisebite.service.UserManagementService;

@RestController
@RequestMapping
public class ClientController {
    private final UserManagementService userManagementService;
    @Autowired
    public ClientController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }
    @GetMapping("/clients/{username}/dietitian-list")
    public boolean isClientOnDietitianList(@PathVariable String username) {
        return userManagementService.isClientOnDietitianList(username);
    }
}
