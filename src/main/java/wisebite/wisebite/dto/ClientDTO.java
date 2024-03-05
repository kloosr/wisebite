package wisebite.wisebite.dto;

import wisebite.wisebite.model.UserTypeEnum;
import wisebite.wisebite.service.validation.ValidHeight;
import wisebite.wisebite.service.validation.ValidWeight;
import wisebite.wisebite.model.Client;
import java.sql.Date;
import java.time.LocalDate;

public class ClientDTO extends UserDTO {
    @ValidHeight
    private final Integer height;

    @ValidWeight
    private final Double weight;

    public ClientDTO(String username, String password, String firstName, String infix, String lastName, Integer height, Double weight) {
        super(username, password, firstName, infix, lastName);
        this.height = height;
        this.weight = weight;
        this.setUserType(UserTypeEnum.CLIENT);
    }
   /* @Override
    public Client convertDTO() {
        Date currentDate = Date.valueOf(LocalDate.now());
        return new Client(
        );
    } */

    public int getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

}
