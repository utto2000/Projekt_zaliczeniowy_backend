package com.wator.organizer.entities;

import javax.persistence.*;

@Entity
@Table(name = "calculator")
public class CalculatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "userId")
    private Integer userId;
    @Column(name = "first_number")
    private Integer first_number;
    @Column(name = "secondNumber")
    private Integer secondNumber;

    public CalculatorEntity() {
    }

    public CalculatorEntity(Integer id, Integer userId, Integer first_number, Integer secondNumber) {
        this.id = id;
        this.userId = userId;
        this.first_number = first_number;
        this.secondNumber = secondNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFirst_number() {
        return first_number;
    }

    public void setFirst_number(Integer first_number) {
        this.first_number = first_number;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }
}
