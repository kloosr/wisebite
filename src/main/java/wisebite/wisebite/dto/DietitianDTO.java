package wisebite.wisebite.dto;

import wisebite.wisebite.model.Dietitian;
import wisebite.wisebite.model.UserTypeEnum;

public class DietitianDTO extends UserDTO{
    public DietitianDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.DIETITIAN);
    }
    @Override
    public Dietitian convertDTO() {
        return new Dietitian(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
