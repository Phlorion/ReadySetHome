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
        this.date = formatDate(date);
        this.status = status;
        this.id = id;
        this.image=image;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return formatDate(date);
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
    private String formatDate(String dateString) {
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = sdfInput.parse(dateString);

            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            return sdfOutput.format(date);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parsing exception
            return dateString; // Return the original string if parsing fails
        }
    }
}
