package com.example.readysethome.model;

import com.example.readysethome.MainActivity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

class Booking {
    private boolean isCancelled = false;
    private BookingRequest bookingRequest;
    int apartmentRating=-1;
    public Booking(BookingRequest bookingRequest) {
        this.bookingRequest=bookingRequest;
    }
    //methodos elegxou na yparxei energh krathsh gia mia xronikh periodo

    public boolean isActive() {
        Date currentDate = new Date(); //today's date
        return currentDate.after(bookingRequest.getCheck_in()) && currentDate.before(bookingRequest.getCheck_out());
    }

    public void rateApartment(int rating) {
        if (isStayCompleted()) {
            apartmentRating = rating;
            System.out.println("Apartment rated with " + rating + " stars.");
        } else {
            System.out.println("Cannot rate the apartment before the stay is completed.");
        }
    }

    // Method to check if stay is completed
    private boolean isStayCompleted() {
        Date currentDate = new Date();
        return currentDate.after(bookingRequest.getCheck_out());
    }

    public int getApartmentRating() {
        return apartmentRating;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
    public void cancel() {
        if (!isCancelled) {
            isCancelled = true;
            bookingRequest.setBooking_status( ReservationStatus.CANCELLED_BY_TENANT);
            double refundAmount = calculateRefundAmount();
            bookingRequest.getTenant().getCreditCard().refund(refundAmount);
            notifyUser(bookingRequest.getTenant(), "Booking Canceled", "Your booking has been canceled successfully. A refund of $" + refundAmount + " has been processed.");
            bookingRequest.getListing().getCalendar().setAvailable(bookingRequest.getCheck_in(), bookingRequest.getCheck_out());



        }
    }

    // Calculates the refund amount
    private double calculateRefundAmount() {
        long days_of_stay = daysBetween(bookingRequest.getCheck_in(), bookingRequest.getCheck_out());
        double totalCost =(days_of_stay * bookingRequest.getListing().getPrice());
        double refundPercentage = (daysUntilCheckIn(new Date()) <= 30) ? 0.95 : 1.0;
        return totalCost * refundPercentage;
    }

    public ReservationStatus getBooking_status() {
        return bookingRequest.getBooking_status();
    }
    // Methodos gia thn enhmerwsh
    private void notifyUser(User user, String Title, String Desc) {
        MainActivity.SYSTEM.getEmail().send(user.getEmail(), Title, Desc);
    }

    // count the days between two given dates
    private long daysBetween(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return diff;
    }

    public long daysUntilCheckIn(Date currentDate) {
        long daysDifference = daysBetween(currentDate, bookingRequest.getCheck_in());
        return daysDifference;
    }


    public int getId() {
        return bookingRequest.getBooking_id();
    }

    public Date getCheckIn() {
        return bookingRequest.getCheck_in();
    }

    public Date getCheckOut() {
        return bookingRequest.getCheck_out();
    }

    public Tenant getTenant() {
        return bookingRequest.getTenant();
    }

    public Listing getListing() {
        return bookingRequest.getListing();
    }

    public void setId(int id) {
        bookingRequest.setBooking_id(id);
    }

    public void setCheckIn(Date checkIn) {
        bookingRequest.setCheck_in(checkIn);
    }

    public void setCheckOut(Date checkOut) {
        bookingRequest.setCheck_out(checkOut);
    }

    public void setTenant(Tenant tenant) {
        bookingRequest.setTenant(tenant);
    }

    public void setListing(Listing listing) {
        bookingRequest.setListing(listing);
    }
}
