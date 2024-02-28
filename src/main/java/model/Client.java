package model;

import org.springframework.scheduling.config.Task;

import java.time.LocalDate;

public class Client extends User{
    private double weight;
    private int height;
    private LocalDate startDate;
    public Client (String username, String password, String firstName, String infix, String lastName, double weight, int height){
        super(username, password, firstName, infix, lastName);
        this.weight = weight;
        this.height = height;
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
