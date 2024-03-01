package wisebite.wisebite.model;

import java.util.ArrayList;
import java.util.List;

public class Coach extends User {
    List<Client> allClients = new ArrayList<>();
    public Coach(String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.COACH);
    }
    public void updateDiet(/*Diet*/){
        //TODO
    }

    public void assignDietToClient (/*Client*/){
        //TODO
    }

    public void createWorkout(){
        //TODO
    }

    public void createExercise(){
        //TODO
    }

    public void createPlan(){
        //todo
    }
    public void planAppointment(){
        //TODO
    }

    public void sendMessage(){
        //TODO
    }

}
