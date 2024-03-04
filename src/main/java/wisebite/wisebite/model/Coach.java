package wisebite.wisebite.model;

public class Coach extends User {
    private String username;
    private String firstName;
    private String lastName;
private String password;
private String infix;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoachUsername() {
        return username;
    }

public void setFirstName(String firstName) {
        this.firstName = firstName;
}
    public String getCoachFirstName() {
        return firstName;
    }
public void setLastName(String lastName){
        this.lastName = lastName;
}
    public String getCoachLastName() {
        return lastName;
    }
}
