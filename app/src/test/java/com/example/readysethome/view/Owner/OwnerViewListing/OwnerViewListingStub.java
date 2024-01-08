package com.example.readysethome.view.Owner.OwnerViewListing;

public class OwnerViewListingStub implements OwnerViewListingView{
    String title, desc, price, income, cancellations, bookings, bookedDays, year;
    int img, month;




    public OwnerViewListingStub() {
        title = desc = price = income = cancellations = bookings = bookedDays = year = "";
        img = month = 0;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public void setImage(int img) {
        this.img = img;
    }

    @Override
    public void setIncomePerMonth(String income) {
        this.income = income;
    }

    @Override
    public void setCancellationsPerMonth(String cancellations) {
        this.cancellations = cancellations;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getIncome() {
        return income;
    }

    public String getCancellations() {
        return cancellations;
    }

    public String getBookings() {
        return bookings;
    }

    public String getBookedDays() {
        return bookedDays;
    }

    public int getImg() {
        return img;
    }

    @Override
    public void setBookingsPerMonth(String bookings) {
        this.bookings = bookings;
    }

    @Override
    public void setOccupancy(String bookedDays) {
        this.bookedDays = bookedDays;
    }

    @Override
    public String getYear() {
        return year;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setCancellations(String cancellations) {
        this.cancellations = cancellations;
    }

    public void setBookings(String bookings) {
        this.bookings = bookings;
    }

    public void setBookedDays(String bookedDays) {
        this.bookedDays = bookedDays;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public int getMonth() {
        return month;
    }
}
