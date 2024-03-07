package wisebite.wisebite.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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
import wisebite.wisebite.model.UserTypeEnum;
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
    Algorithm algorithm = Algorithm.HMAC256("wisebite");
    JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("wisebite").build();

    @Autowired
    public DietitianController(UserManagementService userManagementService, AuthenticationService authenticationService) {
        this.userManagementService = userManagementService;
        this.authenticationService = authenticationService;
        this.mapper = new MapperClient();
        this.mapperClients = new AllClientsMapper();
    }
    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestHeader String dietitian) {
        return ResponseEntity.ok(authenticationService.createToken(dietitian, UserTypeEnum.DIETITIAN));
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

    @GetMapping("dietitian/overview/coaches")
    public List<Coach> getAllCoaches() {
        return userManagementService.getAllCoaches();
    }

    @GetMapping("/sorted-by-bmi")
    public ResponseEntity<List<Client>> getClientsSortedByBmiAndDietitian(String dietitianUsername) {
        List<Client> clients = userManagementService.getAllClientsOfDietitian(dietitianUsername);
        return (ResponseEntity<List<Client>>) userManagementService.sortClientsByBMI(clients);
    }
    public boolean hasAccess (String jwtToken) {
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        if (decodedJWT.getClaim("role").asString().equals("dietitian")) {
            return true;
        } else {
            return false;
        }
    }
}



