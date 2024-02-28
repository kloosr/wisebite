package wisebite.wisebite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisebite.wisebite.model.Admin;
import wisebite.wisebite.model.Client;
import wisebite.wisebite.model.Coach;
import wisebite.wisebite.model.Dietitian;
import wisebite.wisebite.repository.AdminRepository;

@Service
public class AdminService {
    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public String registerClient(Client client) {
        return client.getUsername();
    }
    public String registerDietitian(Dietitian dietitian) {
        return dietitian.getUsername();
    }
    public String registerCoach(Coach coach) {
        return coach.getUsername();
    }
    public String registerAdmin(Admin admin) {
        // TODO PASSWORD HASH
        adminRepository.createUser(admin);
        adminRepository.createAdmin(admin);
        return admin.getUsername();
    }
}
