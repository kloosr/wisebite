package wisebite.wisebite.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import wisebite.wisebite.service.validation.ValidPassword;
import wisebite.wisebite.service.validation.ValidUsername;

public class UserInfo {
    @Size(min = 5, message = "Username should have at least 5 characters.")
    @ValidUsername
    private String username;

    @Size(min = 8, message = "Password should have at least 8 characters.")
    @ValidPassword
    private String password;
    private String hash;

    @NotEmpty
    private final String firstName;

    private final String infix;

    @NotEmpty
    private final String lastName;
    private UserTypeEnum userType;

    public UserInfo(String username, String password, String firstName, String infix, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
    }

    public User convertToUser() {
        return new User(this.username, this.getHash(), this.firstName, this.infix, this.lastName);
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

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }
}
