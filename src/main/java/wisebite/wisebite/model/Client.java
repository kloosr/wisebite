package wisebite.wisebite.model;

import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

public class Client extends User{
    @Setter
    private double weight;
    @Setter
    private int height;
    @Setter
    private Date startDate;
    public Client (String username, String password, String firstName, String infix, String lastName, double weight, int height, Date startDate){
        super(username, password, firstName, infix, lastName);
        this.weight = weight;
        this.height = height;
        this.startDate = startDate;
        this.setUserType(UserTypeEnum.CLIENT);
    }

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Date getStartDate() {
        return startDate;
    }

}
