package wisebite.wisebite.model;

import java.sql.Date;
import java.time.LocalDate;

public class Client extends User{
    private double weight;
    private int height;
    private Date startDate;
    public Client (String username, String password, String firstName, String infix, String lastName, double weight, int height, Date startDate){
        super(username, password, firstName, infix, lastName);
        this.weight = weight;
        this.height = height;
        this.startDate = this.startDate;
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
