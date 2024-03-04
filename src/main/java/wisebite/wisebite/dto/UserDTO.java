package wisebite.wisebite.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Setter;
import wisebite.wisebite.model.Admin;
import wisebite.wisebite.model.User;
import wisebite.wisebite.model.UserTypeEnum;
import wisebite.wisebite.service.validation.ValidUsername;

public class UserDTO {
    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters.")
    @ValidUsername
    private String username;

    @NotEmpty
    @Size(min = 8, message = "Password should have at least 8 characters.")
    private String password;

    @NotEmpty
    private String firstName;

    private String infix;

    @NotEmpty
    private String lastName;
    private UserTypeEnum userType;

    public UserDTO(String username, String password, String firstName, String infix, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
    }

    public User convertDTO() {
        return new User(this.username, this.password, this.firstName, this.infix, this.lastName);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getInfix() {
        return infix;
    }

    public String getLastName() {
        return lastName;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }
    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }

}
