package com.phonepe.vehiclerenting.booking.strategy.factory;

import com.phonepe.vehiclerenting.booking.dto.BookingResponse;
import com.phonepe.vehiclerenting.booking.BookingService;
import com.phonepe.vehiclerenting.booking.dto.BookingDTO;

public interface BookingStrategy {

    BookingResponse bookVehicle(BookingService bookingService, BookingDTO bookingDTO);
    BookingStrategyType bookingStrategyType();
}
