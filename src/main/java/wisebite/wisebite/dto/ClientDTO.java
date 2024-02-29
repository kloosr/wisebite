package wisebite.wisebite.dto;

import wisebite.wisebite.model.Client;
import jakarta.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

public class ClientDTO {
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

    @NotNull
    @ValidHeight
    private Integer height;

    @NotNull
    @ValidWeight
    private Double weight;

    public ClientDTO(String username, String password, String firstName, String infix, String lastName, int height, double weight) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
    }
    public Client convertToClient() {
        Date currentDate = Date.valueOf(LocalDate.now());
        return new Client(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName(), this.getWeight(), this.getHeight(),
                currentDate);
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

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

}
