package com.example.readysethome.memorydao;

import com.example.readysethome.dao.BookingRequestDAO;
import com.example.readysethome.model.BookingRequest;


import java.util.ArrayList;
import java.util.List;

public class BookingRequestDAOMemory implements BookingRequestDAO {
    protected static ArrayList<BookingRequest> bookingRequests = new ArrayList<>();

    @Override
    public void delete(BookingRequest bookingRequest) {
        bookingRequests.remove(bookingRequest);
    }

    @Override
    public void save(BookingRequest bookingRequest) {
        bookingRequests.add(bookingRequest);
    }

    @Override
    public List<BookingRequest> findAll() {
        return new ArrayList<>(bookingRequests);
    }

    @Override
    public BookingRequest findByID(int id) {
        for (BookingRequest bookingRequest : bookingRequests) {
            if (bookingRequest.getBooking_id() == id) {
                return bookingRequest;
            }
        }
        return null;
    }


}
