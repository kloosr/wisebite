package wisebite.wisebite.service;

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
        if (adminRepository.usernameExists(user.getUsername())) {
            // TODO RETURN ERROR?
        } else {
            // TODO PASSWORD HASH
            UserTypeEnum userType = user.getUserType();
            switch (userType) {
                case ADMIN: adminRepository.createAdmin((Admin)user);
                case CLIENT:
                case DIETITIAN:
                case COACH:
                default: break;

            }

        }
        return user.getUsername();
    }
}
