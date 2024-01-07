package com.example.readysethome.memorydao;

import com.example.readysethome.dao.BookingsDAO;
import com.example.readysethome.model.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Υλοποίηση του BookingsDAO για αποθήκευση στη μνήμη.
 */
public class BookingsDAOMemory implements BookingsDAO {

    private final List<Booking> bookings;

    public BookingsDAOMemory() {
        this.bookings = new ArrayList<>();
    }

    @Override
    public boolean delete(Booking booking) {
       return bookings.remove(booking);
    }

    @Override
    public void save(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    @Override
    public Booking findByID(int id) {
        for (Booking booking : bookings) {
            if (booking.getId() == id) {
                return booking;
            }
        }
        return null;
    }
}
