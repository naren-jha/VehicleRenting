package com.njha.vehiclerenting.booking;

import com.njha.vehiclerenting.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository {

    @Autowired
    private Database db;

    public void addBooking(Booking booking) {
        db.addBooking(booking);
    }
}
