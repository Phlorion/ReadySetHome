import java.util.ArrayList;

class Renter {
    ArrayList<Date> bookings;

    public Renter() {
        this.bookings = new ArrayList<>();
    }

    public void makeReservation(Apartment apartment, Date checkIn, Date checkOut) {
        BookingRequest bookingRequest = new BookingRequest();
    }

    public void cancelReservation(Booking booking) {
        deleteBooking(booking.getId());
    }

    public void deleteBooking(int bookingId) {
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getId() == bookingId) {
                iterator.remove();
                break;
            }
        }
    }

    public void viewBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
    // Setters kai getters
    public Date[][] getBookings(){
        return bookings;
    }

    public void setBookings(Date[][] bookings){
        this.bookings=bookings;
    }


}
