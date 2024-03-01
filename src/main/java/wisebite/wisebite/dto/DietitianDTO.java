package wisebite.wisebite.dto;

import wisebite.wisebite.model.Dietitian;

public class DietitianDTO extends UserDTO{
    public DietitianDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
    }
    public Dietitian convertToDietitian() {
        return new Dietitian(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName());
    }
}
