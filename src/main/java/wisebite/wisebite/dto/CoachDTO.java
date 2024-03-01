package wisebite.wisebite.dto;

import wisebite.wisebite.model.Coach;

public class CoachDTO extends UserDTO {
    public CoachDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
    }
    public Coach convertToCoach() {
        return new Coach(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
