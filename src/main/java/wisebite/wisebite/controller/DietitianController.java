package wisebite.wisebite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wisebite.wisebite.dto.AllClientsDTO;
import wisebite.wisebite.dto.AllClientsMapper;
import wisebite.wisebite.dto.ClientDTO;
import wisebite.wisebite.dto.MapperClient;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.model.Plan;
import wisebite.wisebite.service.AuthenticationService;
import wisebite.wisebite.service.UserManagementService;

import java.util.List;

@RestController
@RequestMapping("/dietitian")
public class DietitianController {
    private final UserManagementService userManagementService;
    private final AuthenticationService authenticationService;
    private MapperClient mapper;
    private AllClientsMapper mapperClients;

    @Autowired
    public DietitianController(UserManagementService userManagementService, AuthenticationService authenticationService) {
        this.userManagementService = userManagementService;
        this.authenticationService = authenticationService;
        this.mapper = new MapperClient();
        this.mapperClients = new AllClientsMapper();
    }
    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestHeader String dietitian) {
        return ResponseEntity.ok(authenticationService.login(dietitian));
    }

    @GetMapping("overview/{username}")
    public ResponseEntity<List<AllClientsDTO>> getAllClientsOfDietitian(@PathVariable String username, @RequestHeader String jwtToken) {
        if (!userManagementService.clientExists(username)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (!userManagementService.isClientOnDietitianList(username, authenticationService.getUsername(jwtToken)) || !hasAccess(jwtToken)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else {
            List<Client> clients = userManagementService.getAllClientsOfDietitian(username);
            {
                if (clients != null) {
                    List<AllClientsDTO> allClientsDTO = mapperClients.toDto(clients);
                    return new ResponseEntity<>(allClientsDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }
        }
    }

    @GetMapping("/overview/client/{username}")
    public ResponseEntity<?> getSingleClient(@PathVariable String username) {
        Client client = userManagementService.getSingleClient(username);

        if (client != null) {
            ClientDTO clientDTO = mapper.toDto(client);
         return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("dietitian/overview/client")
    public ResponseEntity<Client> getClientInformation(@RequestParam("username") String username) {
        Client client = userManagementService.getSingleClient(username);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!userManagementService.isClientOnDietitianList(username)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("dietitian/overview/coaches")
    public List<Coach> getAllCoaches() {
        return userManagementService.getAllCoaches();
    }

    @GetMapping("/sorted-by-bmi")
    public ResponseEntity<List<Client>> getClientsSortedByBmiAndDietitian(String dietitianUsername) {
        List<Client> clients = userManagementService.getAllClientsOfDietitian(dietitianUsername);
        return (ResponseEntity<List<Client>>) userManagementService.sortClientsByBMI(clients);
    }
    private boolean hasAccess(String jwtToken) {
        return authenticationService.hasAcces(jwtToken);
    }
}

    @PostMapping("/dietitian/overview/{username}")
    public ResponseEntity<?> assignCoachToClient(@PathVariable @RequestBody String username, @RequestBody String coach, UriComponentsBuilder ucb) {
        if (coach == null || coach.isEmpty() || username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Coach username and client username are required.");
        } else {
            return ResponseEntity.ok("Coach assigned to client successfully.");
        }
    }
}


