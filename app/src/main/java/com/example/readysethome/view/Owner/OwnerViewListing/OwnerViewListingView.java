package com.example.readysethome.view.Owner.OwnerViewListing;

public interface OwnerViewListingView {
    void setTitle(String title);
    void setDesc(String desc);
    void setPrice(String price);
    void setImage(int img);
    void setIncomePerMonth(String income);
    void setCancellationsPerMonth(String cancellations);
    void setBookingsPerMonth(String bookings);
    void setRating(String rating);

    void setOccupancy(String bookedDays);

    String getYear();

    int getMonth();
}
