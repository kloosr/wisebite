package wisebite.wisebite.dto;

import wisebite.wisebite.model.Admin;
import wisebite.wisebite.model.UserTypeEnum;

public class AdminDTO extends UserDTO{
    public AdminDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.ADMIN);
    }
    @Override
    public Admin convertDTO() {
        return new Admin(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
