package wisebite.wisebite.service;

import org.junit.jupiter.api.Test;
import wisebite.wisebite.database.UserDAO;
import wisebite.wisebite.repository.AdminRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AuthenticationServiceUnitTest {
    AdminRepository adminRepository =  mock(AdminRepository.class );
    private final AuthenticationService authenticationService = new AuthenticationService(adminRepository);

    @Test
    public void hashAndTestString() {
        String password = "Geheimwoord";
        String wrongPassword = "Geheimwoord7";
        String hash = authenticationService.createHash(password);
        assertTrue(authenticationService.checkPassword(password, hash));
        assertFalse(authenticationService.checkPassword(wrongPassword, hash));

    }




}
