package com.example.naman.namanapp;

import com.example.naman.namanapp.activities.DriverFiles.Expenditure;

import java.util.HashMap;

/**
 * Created by Priyam on 13/03/19.
 */

public class Driver {

    public String id;
    public String name;
    public String email;
    public String carname;
    public String carno;
    public String contact;
    public String amount;
    public String distance;
    public String usertype;
    public HashMap<String, Expenditure> Expenditure;


    Driver(String id, String name, String email, String carname, String carno, String contact, String amount, String distance, HashMap<String, Expenditure> expenditureHashMap) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.carname = carname;
        this.carno = carno;
        this.contact = contact;
        this.amount = amount;
        this.distance = distance;
        this.Expenditure = expenditureHashMap;
    }

    public Driver(String id, String name, String email, String carname, String carno, String contact, String amount, String distance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.carname = carname;
        this.carno = carno;
        this.contact = contact;
        this.amount = amount;
        this.distance = distance;
    }

    public Driver(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Driver() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCarname() {
        return carname;
    }

    public String getCarno() {
        return carno;
    }

    public String getContact() {
        return contact;
    }

    public String getAmount() {
        return amount;
    }

    public String getDistance() {
        return distance;
    }

    public String getUsertype() {
        return usertype;
    }

    public HashMap<String, Expenditure> getExpenditure() {
        return Expenditure;
    }
}
