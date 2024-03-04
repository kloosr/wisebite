package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.*;
import wisebite.wisebite.repository.AdminRepository;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final String PEPPER = "51ee225ad94608e5508ac1e49d47ac9a";

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String registerUser(User user) {
        hashPassword(user);
        adminRepository.createUser(user);
        return user.getUsername();
    }
    public void deleteUser(User user) {
        adminRepository.deleteUser(user);
    }
    public boolean usernameExists(String username) {
        Optional<User> retrievedUser = adminRepository.getUserByUsername(username);
        return retrievedUser.isPresent();
    }
    public void hashPassword(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), PEPPER + BCrypt.gensalt()));
    }
    public boolean checkPassword(User user, String password) {
        Optional<User> retrievedUser = adminRepository.getUserByUsername(user.getUsername());
        if (retrievedUser.isPresent()) {
            String hash = retrievedUser.get().getPassword();
            return BCrypt.checkpw(password, hash);
        } else {
            return false;
        }
    }
}
