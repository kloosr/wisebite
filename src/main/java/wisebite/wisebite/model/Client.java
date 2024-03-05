package wisebite.wisebite.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.time.LocalDate;

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

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Date getStartDate() {
        return startDate;
    }

    public double calculateBMI(double weight, int height){
        //TODO
        return 0;
    }

    public void planAppointment(){
        //TODO
    }

    public void sendMessage() {
        //TODO
    }

    public void completeTask(/*Task*/){
        //TODO
    }

    public void setWeight(){
        //TODO
    }

    public void addReceipe (){
        //TODO
        //optioneel
    }
}
