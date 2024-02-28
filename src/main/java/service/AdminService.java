package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AdminRepository;

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
        return admin.getUsername();
    }
}
