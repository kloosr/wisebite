package wisebite.wisebite.dto;

import java.util.Date;
//This class is used to transfer data between processes.
public class DailyTaskDTO {
    private int id;
    private Date date;
    private boolean dailyGoal;

    public DailyTaskDTO(int id, Date date, boolean dailyGoal) {
        this.id = id;
        this.date = date;
        this.dailyGoal = dailyGoal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDailyGoal() {
        return dailyGoal;
    }

    public void setDailyGoal(boolean dailyGoal) {
        this.dailyGoal = dailyGoal;
    }
}
