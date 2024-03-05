package wisebite.wisebite.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.UserInfo;
import wisebite.wisebite.model.*;
import wisebite.wisebite.repository.AdminRepository;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String registerUser(UserInfo userInfo) {
        User user = convertToUser(userInfo);
        hashPassword(user);
        adminRepository.createUser(user);
        return user.getUsername();
    }
    public ResponseEntity<String> deleteUser(UserInfo userInfo) {
        if (adminRepository.getUserByUsername(userInfo.getUsername()).isPresent()) {
            adminRepository.deleteUser(userInfo.getUsername());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist.");
        }
    }
    public boolean usernameExists(String username) {
        Optional<User> retrievedUser = adminRepository.getUserByUsername(username);
        return retrievedUser.isPresent();
    }
    public void hashPassword(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
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
    private User convertToUser(UserInfo userInfo) {
        return userInfo.convertDTO();
    }
}
