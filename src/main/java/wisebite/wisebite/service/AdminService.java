package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.*;
import wisebite.wisebite.repository.AdminRepository;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

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
    public boolean usernameExists(User user) {
        return adminRepository.usernameExists(user.getUsername());
    }
    public void hashPassword(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
    }
}
