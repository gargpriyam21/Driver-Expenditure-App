package com.example.naman.namanapp.activities.DriverActivities;

/**
 * Created by Neera on 25/03/19.
 */

public class Expenditure {

    public String datetime;
    public String Fuel;
    public String Toll;
    public String Personal;
    public String Maintenance;
    public String Insurance;
    public String Total;

    public Expenditure(String datetime, String fuel, String toll, String personal, String maintenance, String insurance, String total) {
        this.datetime = datetime;
        Fuel = fuel;
        Toll = toll;
        Personal = personal;
        Maintenance = maintenance;
        Insurance = insurance;
        Total = total;
    }

    public String getTotal() {
        return Total;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getFuel() {
        return Fuel;
    }

    public String getToll() {
        return Toll;
    }

    public String getPersonal() {
        return Personal;
    }

    public String getMaintenance() {
        return Maintenance;
    }

    public String getInsurance() {
        return Insurance;
    }
}
