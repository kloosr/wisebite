package wisebite.wisebite.model;

import java.time.LocalDate;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String infix;
    private String lastName;
    private String salt;
    private UserTypeEnum userType;

    public User(String username, String password, String salt, String firstName, String infix, String lastName){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
        this.salt = salt;
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

    public UserTypeEnum getUserType() {
        return userType;
    }
    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
