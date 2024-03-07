package wisebite.wisebite.model;

public class Coach extends User {

    public Coach(String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.COACH);
    }
}
