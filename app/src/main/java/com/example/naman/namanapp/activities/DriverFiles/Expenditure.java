package com.example.naman.namanapp.activities.DriverFiles;

/**
 * Created by Priyam on 25/03/19.
 */

public class Expenditure {

    public String datetime;
    public String Amount;
    public String Reason;

    public Expenditure(String datetime, String amount, String reason) {
        this.datetime = datetime;
        this.Amount = amount;
        this.Reason = reason;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getAmount() {
        return Amount;
    }

    public String getReason() {
        return Reason;
    }
}