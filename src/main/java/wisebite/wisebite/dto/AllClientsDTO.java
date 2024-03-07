package wisebite.wisebite.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AllClientsDTO {
    private String firstName;
    private String infix;
    private String lastName;
    public AllClientsDTO(String firstName, String infix, String lastName) {
        this.firstName = firstName;
        this.infix = infix;
        this.lastName = lastName;

    }
    public AllClientsDTO(){};
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getInfix() {return infix;}
    public void setInfix(String infix) {this.infix = infix;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
}
