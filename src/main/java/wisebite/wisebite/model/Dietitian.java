package wisebite.wisebite.model;

import java.util.ArrayList;
import java.util.List;

public class Dietitian extends User {

    private List<Client> allClients = new ArrayList<>();
    private List<Coach> allCoaches = new ArrayList<>();

    public Dietitian (String username, String password, String firstName, String infix, String lastName){
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.DIETITIAN);
    }

    public void getAllClients(){
        //TODO
    }

    public void getAllCoaches(){
        //TODO
    }

    public void createDiet(){
        //TODO
    }

    public void createRecipe(){
        //TODO
    }
    public void assignCoachToClient(){
        //TODO
    }

    public void assignDietToClient (){
        //TODO
    }
}
