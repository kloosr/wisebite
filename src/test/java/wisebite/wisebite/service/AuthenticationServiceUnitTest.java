package wisebite.wisebite.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceUnitTest {
    private final AuthenticationService authenticationService = new AuthenticationService();

    @Test
    public void hashAndTestString() {
        String password = "Geheimwoord";
        String wrongPassword = "Geheimwoord7";
        String hash = authenticationService.createHash(password);
        assertTrue(authenticationService.checkPassword(password, hash));
        assertFalse(authenticationService.checkPassword(wrongPassword, hash));

    }




}
