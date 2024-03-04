package wisebite.wisebite.dto;

import wisebite.wisebite.service.validation.ValidHeight;
import wisebite.wisebite.service.validation.ValidWeight;
import wisebite.wisebite.model.Client;
import jakarta.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

public class ClientDTO extends UserDTO {
    @NotNull
    @ValidHeight
    private Integer height;

    @NotNull
    @ValidWeight
    private Integer weight;

    public ClientDTO(String username, String password, String firstName, String infix, String lastName, Integer height, Integer weight) {
        super(username, password, firstName, infix, lastName);
        this.height = height;
        this.weight = weight;
    }
    public Client convertToClient() {
        Date currentDate = Date.valueOf(LocalDate.now());
        return new Client(this.getUsername(), this.getPassword(), this.getFirstName(),
                this.getInfix(), this.getLastName(), this.getWeight(), this.getHeight(),
                currentDate);
    }

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

}
