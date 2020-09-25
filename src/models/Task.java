package models;
import java.util.Date;

public class Task {
    private long id;
    private String name;
    private String description;
    private boolean finished;
    private Date finished_date;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean getFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    public Date getFinishedDate() {
        return finished_date;
    }
    public void setDataFinishedDate(Date finished_date) {
        this.finished_date = finished_date;
    }
}
