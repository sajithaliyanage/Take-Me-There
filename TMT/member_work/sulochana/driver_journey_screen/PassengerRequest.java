package com.teamwhileloop.my_uis;

/**
 * Created by sulochana on 10/26/16.
 */

public class PassengerRequest {
    String gender,name;
    int distance;

    public PassengerRequest(int passenger_id, String name, int distance,  String gender, double rating,  int reports, int commends) {
        this.rating = rating;
        this.passenger_id = passenger_id;
        this.reports = reports;
        this.commends = commends;
        this.distance = distance;
        this.name = name;
        this.gender = gender;
    }

    public int getCommends() {
        return commends;
    }

    public void setCommends(int commends) {
        this.commends = commends;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    int commends;
    int reports;
    int passenger_id;
    double rating;
}
