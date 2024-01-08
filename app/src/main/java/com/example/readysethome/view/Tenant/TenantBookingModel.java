package com.example.readysethome.view.Tenant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TenantBookingModel {
    private String title;
    private String date;
    private String status;
    private int id;
    private int image;

    public TenantBookingModel(String title, String date, String status, int id,int image) {
        this.title = title;
        this.date = date;
        this.status = status;
        this.id = id;
        this.image=image;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return transformDate(date);
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }
    private String transformDate(String inputDate) {
        try {

            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.US);
            Date date = inputFormat.parse(inputDate);


            SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.US);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return inputDate;
        }
    }
}
