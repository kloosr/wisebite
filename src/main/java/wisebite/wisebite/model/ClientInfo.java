package wisebite.wisebite.model;

import wisebite.wisebite.service.validation.ValidHeight;
import wisebite.wisebite.service.validation.ValidWeight;

import java.sql.Date;
import java.time.LocalDate;

public class ClientInfo extends UserInfo {
    @ValidHeight
    private final Integer height;

    @ValidWeight
    private final Double weight;

    public ClientInfo(String username, String password, String firstName, String infix, String lastName, Integer height, Double weight) {
        super(username, password, firstName, infix, lastName);
        this.height = height;
        this.weight = weight;
        this.setUserType(UserTypeEnum.CLIENT);
    }
    @Override
    public Client convertDTO() {
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
