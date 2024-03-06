package wisebite.wisebite.dto;

import java.util.Date;

public class ClientDTO {
    private String firstName;
    private String infix;
    private String lastName;
    private double weight;
    private int height;
    private Date startDate;

    public ClientDTO() {
    }

    public ClientDTO(String firstName, String infix, String lastName, double weight, int height, Date startDate) {
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;
        this.height = height;
        this.startDate = startDate;
    }
}
