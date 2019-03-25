package com.example.naman.namanapp;

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


    Driver(String Uid, String n, String e, String cn, String cno, String cont, String am, String dist) {

        id = Uid;
        name = n;
        email = e;
        carname = cn;
        carno = cno;
        contact = cont;
        amount = am;
        distance = dist;
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
}
