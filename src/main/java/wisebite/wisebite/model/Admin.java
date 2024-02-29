package wisebite.wisebite.model;

public class Admin extends  User{
    public Admin (String username, String password, String firstName, String infix, String lastName){
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.ADMIN);
    }

}
