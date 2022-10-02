package com.phonepe.vehiclerenting.booking;

import com.phonepe.vehiclerenting.Database;
import com.phonepe.vehiclerenting.branch.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    private Database db;

    public void addBooking(Booking booking) {
        db.addBooking(booking);
    }
}
