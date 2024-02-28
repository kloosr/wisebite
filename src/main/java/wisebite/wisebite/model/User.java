package wisebite.wisebite.model;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String infix;
    private String lastName;

    public User(String username, String password, String firstName, String infix, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
    }

    public void planAppointment(){
        //TODO
    }

    public void sendMessage(){
        //TODO
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getInfix() {
        return infix;
    }

    public String getLastName() {
        return lastName;
    }
}
