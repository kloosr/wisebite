package wisebite.wisebite.dto;

import java.util.Date;

public class ClientDTO {
    private String firstName;
    private String infix;
    private String lastName;
    private double weight;
    private int height;
    private Date startDate;

    public ClientDTO(String firstName, String infix, String lastName, double weight, int height , Date startDate) {
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
        this.weight = weight;
        this.height = height;
        this.startDate = startDate;
    }

    public ClientDTO() {
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}