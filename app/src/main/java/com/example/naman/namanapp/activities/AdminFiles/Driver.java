package com.example.naman.namanapp.activities.AdminFiles;

import java.net.URL;

/**
 * Created by Neera on 01/02/19.
 */

public class Driver {

    private int id;
    private int Name;
    private int CarName;
    private int CarNo;
    private URL Photo;


    public Driver(int id, int name, int carName, int carNo, URL photo) {
        this.id = id;
        Name = name;
        CarName = carName;
        CarNo = carNo;
        Photo = photo;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return Name;
    }

    public int getCarName() {
        return CarName;
    }

    public int getCarNo() {
        return CarNo;
    }

    public URL getPhoto() {
        return Photo;
    }
}
