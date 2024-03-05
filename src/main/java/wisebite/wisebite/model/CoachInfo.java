package wisebite.wisebite.model;

public class CoachInfo extends UserInfo {
    public CoachInfo(String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.COACH);
    }
    @Override
    public Coach convertDTO() {
        return new Coach(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
