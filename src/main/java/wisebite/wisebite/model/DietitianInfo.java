package wisebite.wisebite.model;

public class DietitianInfo extends UserInfo {
    public DietitianInfo(String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.DIETITIAN);
    }
    @Override
    public Dietitian convertToUser() {
        return new Dietitian(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
