package wisebite.wisebite.service;

import org.junit.jupiter.api.Test;
import wisebite.wisebite.database.UserDAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AuthenticationServiceUnitTest {
    UserDAO userDAO =  mock(UserDAO.class );
    private final AuthenticationService authenticationService = new AuthenticationService(userDAO);

    @Test
    public void hashAndTestString() {
        String password = "Geheimwoord";
        String wrongPassword = "Geheimwoord7";
        String hash = authenticationService.createHash(password);
        assertTrue(authenticationService.checkPassword(password, hash));
        assertFalse(authenticationService.checkPassword(wrongPassword, hash));

    }




}
