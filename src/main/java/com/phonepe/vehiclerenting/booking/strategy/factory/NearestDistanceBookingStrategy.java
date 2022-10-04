package com.phonepe.vehiclerenting.booking.strategy.factory;

import com.phonepe.vehiclerenting.booking.dto.BookingResponse;
import com.phonepe.vehiclerenting.booking.BookingService;
import com.phonepe.vehiclerenting.booking.dto.BookingDTO;
import com.phonepe.vehiclerenting.exception.NotImplementedBookingStrategyException;
import com.phonepe.vehiclerenting.vehicle.Vehicle;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class NearestDistanceBookingStrategy implements BookingStrategy {

    @Override
    public Vehicle selectVehicle(BookingService bookingService, BookingDTO bookingDTO) {
        // TODO: to be implemented when needed
        throw new NotImplementedBookingStrategyException("NearestDistanceRentalStrategy is not yet supported.");
    }

    @Override
    public BookingStrategyType bookingStrategyType() {
        return BookingStrategyType.NEAREST_DISTANCE;
    }
}
