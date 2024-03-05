package wisebite.wisebite.service;

import org.junit.jupiter.api.Test;
import wisebite.wisebite.database.UserDAO;
import wisebite.wisebite.model.UserInfo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceUnitTest {
    private UserDAO mockUserDAO = mock(UserDAO.class);
    private AuthenticationService authenticationService = new AuthenticationService(mockUserDAO);
    private UserInfo userInfo = mock(UserInfo.class);

    @Test
    public void hashAndTestString() {
        String password = "Geheimwoord";
        when(userInfo.getPassword()).thenReturn(password);
        String hash = authenticationService.createHash(userInfo);
        assert(authenticationService.checkPassword(password, hash));

    }




}
