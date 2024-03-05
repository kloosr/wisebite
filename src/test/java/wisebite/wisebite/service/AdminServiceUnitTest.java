package wisebite.wisebite.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import wisebite.wisebite.model.Admin;
import wisebite.wisebite.model.User;
import wisebite.wisebite.repository.AdminRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ComponentScan("wisebite.wisebite")
public class AdminServiceUnitTest {

    private AdminRepository adminRepository;
    String username = "Testuser";

    @Autowired
    public  AdminServiceUnitTest(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @BeforeEach
    public void start() {
        adminRepository.deleteUser(username);
    }
    @Test
    public void addAndFindUser() {
        User testUser1 = new Admin(username, "123456789", "Mark", "van","Alst");
        adminRepository.createUser(testUser1);
        User testUser2 = adminRepository.getUserByUsername(username).get();

        assertEquals(username, testUser2.getUsername());
        assertEquals(testUser1.getLastName(), testUser2.getLastName());

    }
}
