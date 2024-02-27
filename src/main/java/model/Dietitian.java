package model;

import java.util.ArrayList;
import java.util.List;

public class Dietitian extends User {

    List<Client> allClients = new ArrayList<>();
    List<Coach> allCoaches = new ArrayList<>();

    public Dietitian (String username, String password, String firstName, String lastName){
        super(username, password, firstName, lastName);
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
