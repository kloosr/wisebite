package wisebite.wisebite.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    @Size(min = 8, message = "Password should have at least 8 characters.")
    private String password;

    @NotEmpty
    private String firstName;

    private String infix;

    @NotEmpty
    private String lastName;

    public UserDTO(String username, String password, String firstName, String infix, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
