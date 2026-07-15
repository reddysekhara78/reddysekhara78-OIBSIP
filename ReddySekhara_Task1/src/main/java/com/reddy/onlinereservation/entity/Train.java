package com.reddy.onlinereservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name="trains")
public class Train {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String trainNumber;

    private String trainName;

    private String source;

    private String destination;

    public Train() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber=trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName=trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source=source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination=destination;
    }
}