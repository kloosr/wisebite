package wisebite.wisebite.dto;

import wisebite.wisebite.model.Admin;

public class AdminDTO extends UserDTO{
    public AdminDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
    }
    public Admin convertToDietitian() {
        return new Admin(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
