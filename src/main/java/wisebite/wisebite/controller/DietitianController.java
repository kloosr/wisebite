package wisebite.wisebite.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.service.UserManagementService;

import java.util.Optional;

@RestController
@RequestMapping("/doctor/overview")
public class DietitianController {
    @Autowired
        private UserManagementService userManagementService;
    @GetMapping("/client/{doctorUsername}/{username}")
    public ResponseEntity<?> getClientInformation(@PathVariable("doctorUsername") String doctorUsername,
                                                  @PathVariable("username") String username) {

        // Check if the client exists
        Optional<Client> client = userManagementService.getClientByUsername(username);
        if (client == null) {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
        // Check if the client is in the doctor's list of clients
        if (!userManagementService.isClientAssignedToDoctor(username, doctorUsername)) {
            return new ResponseEntity<>("Client not assigned to this doctor", HttpStatus.UNAUTHORIZED);
        }

        // Return the client's information
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
