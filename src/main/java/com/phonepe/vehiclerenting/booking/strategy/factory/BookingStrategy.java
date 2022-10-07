package com.phonepe.vehiclerenting.booking.strategy.factory;

import com.phonepe.vehiclerenting.booking.BookingService;
import com.phonepe.vehiclerenting.booking.dto.BookingDTO;
import com.phonepe.vehiclerenting.vehicle.Vehicle;

public interface BookingStrategy {

    Vehicle selectVehicle(BookingDTO bookingDTO);
    BookingStrategyType bookingStrategyType();
}
