package wisebite.wisebite.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class Client extends User{
    private double weight;
    private int height;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
    public Client (String username, String password, String firstName, String infix, String lastName, double weight, int height, Date startDate){
        super(username, password, firstName, infix, lastName);
        this.weight = weight;
        this.height = height;
        this.startDate = startDate;
        this.setUserType(UserTypeEnum.CLIENT);
    }
    public Client(String username, String firstName, String infix, String lastName, double weight, int height, Date startDate) {
        super(username, "", firstName, infix, lastName);
        this.weight = weight;
        this.height = height;
        this.startDate = startDate;
        this.setUserType(UserTypeEnum.CLIENT);
    }
    public Client(){super();};
    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
