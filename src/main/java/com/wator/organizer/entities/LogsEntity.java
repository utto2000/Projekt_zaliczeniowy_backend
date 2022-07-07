package com.wator.organizer.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logs")
public class LogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userid;

    @Column(name = "date")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setuserId(Integer user_id) {
        this.userid = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LogsEntity() {
    }

    public LogsEntity(Integer id, Integer user_id, Date date) {
        this.id = id;
        this.userid = user_id;
        this.date = date;
    }
}


