package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String activity;
    private LocalTime time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = LocalTime.parse(time);
    }
}
