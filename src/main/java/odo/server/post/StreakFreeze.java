package odo.server.post;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class StreakFreeze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private int count;

    @Column(name = "date")
    private Date date;



    public void setCount(int count) {
        this.count = count;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
 