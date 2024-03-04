package wisebite.wisebite.dto;

import wisebite.wisebite.model.Coach;

public class CoachDTO extends UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    public CoachDTO (String username, String password, String firstName, String infix, String lastName) {
        super(username, password, firstName, infix, lastName);
    }
    public Coach convertToCoach() {
        return null;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
