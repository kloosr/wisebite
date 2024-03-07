package wisebite.wisebite.model;

import java.util.ArrayList;
import java.util.List;

public class Dietitian extends User {

    List<Client> allClients = new ArrayList<>();
    List<Coach> allCoaches = new ArrayList<>();

    public Dietitian (String username, String password, String firstName, String infix, String lastName){
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.DIETITIAN);
    }
}
