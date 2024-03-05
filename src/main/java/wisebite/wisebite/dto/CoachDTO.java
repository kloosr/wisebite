package wisebite.wisebite.dto;

import wisebite.wisebite.model.Coach;
import wisebite.wisebite.model.UserTypeEnum;

public class CoachDTO extends UserDTO {
    public CoachDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
        this.setUserType(UserTypeEnum.COACH);
    }
    @Override
    public Coach convertDTO() {
        return new Coach(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
