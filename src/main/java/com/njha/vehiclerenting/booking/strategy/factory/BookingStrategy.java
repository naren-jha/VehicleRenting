package com.njha.vehiclerenting.booking.strategy.factory;

import com.njha.vehiclerenting.booking.dto.BookingDTO;
import com.njha.vehiclerenting.vehicle.Vehicle;

public interface BookingStrategy {

    Vehicle selectVehicle(BookingDTO bookingDTO);
    BookingStrategyType bookingStrategyType();
}
