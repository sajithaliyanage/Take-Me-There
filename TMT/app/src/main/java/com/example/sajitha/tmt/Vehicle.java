package com.example.sajitha.tmt;

/**
 * Created by sulochana on 10/19/16.
 */

public class Vehicle {
    public Vehicle(String maker, String model, int total_seats, int driver_id, float rate,boolean ac) {
        Maker = maker;
        Model = model;
        this.ac = ac;
        this.setAvailable_seats(total_seats);
        this.total_seats = total_seats;
        this.driver_id = driver_id;
        this.rate = rate;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    private boolean ac;

    private enum Status{
        UNAVAILABLE(0),AVAILABLE(1),SHARED(2),UNSHARED(3),FULL(4),STEALTH(5);
        private int value;

        Status(int i) {
            this.value = i;
        }
    }

    public enum VehicleType{
        TUK(0),MINI(1),CAR(2),VAN(3),BUS(4),LORRY(5),BICYCLE(6),SUV(7),CAB(8);
        int value;
        VehicleType(int i) {
        }
    }
    private String Maker,Model,Current_Location,Destination;
    private int total_seats;
    private int available_seats;
    private int driver_id;
    private int passengers[];

    public int getExperience_points() {
        return experience_points;
    }

    public void setExperience_points(int experience_points) {
        this.experience_points = experience_points;
    }

    private int experience_points;
    private float rate,avarage_speed;
    private Status current_status;

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCurrent_Location() {
        return Current_Location;
    }

    public void setCurrent_Location(String current_Location) {
        Current_Location = current_Location;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int[] getPassengers() {
        return passengers;
    }

    public void setPassengers(int[] passengers) {
        this.passengers = passengers;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAvarage_speed() {
        return avarage_speed;
    }

    public void setAvarage_speed(float avarage_speed) {
        this.avarage_speed = avarage_speed;
    }

    public Status getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(Status current_status) {
        this.current_status = current_status;
    }
}
