package wisebite.wisebite.model;

public class AdminInfo extends UserInfo {
    public AdminInfo(String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.ADMIN);
    }
    @Override
    public Admin convertToUser() {
        return new Admin(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
