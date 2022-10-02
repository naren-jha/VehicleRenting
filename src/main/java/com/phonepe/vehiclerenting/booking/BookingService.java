package com.phonepe.vehiclerenting.booking;

import com.phonepe.vehiclerenting.booking.dto.BookingDTO;
import com.phonepe.vehiclerenting.booking.dto.BookingResponse;
import com.phonepe.vehiclerenting.booking.strategy.factory.BookingStrategy;
import com.phonepe.vehiclerenting.booking.strategy.factory.BookingStrategyFactory;
import com.phonepe.vehiclerenting.booking.strategy.factory.BookingStrategyType;
import com.phonepe.vehiclerenting.vehicle.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingStrategyFactory bookingStrategyFactory;

    public BookingResponse bookVehicle(BookingDTO bookingDTO) {
        BookingStrategy bookingStrategy = bookingStrategyFactory.getBookingStrategy(BookingStrategyType.LOWEST_PRICE);
        return bookingStrategy.bookVehicle(this, bookingDTO);
    }

    public void addBooking(BookingDTO bookingDTO, Vehicle vehicle) {
        Booking booking = Booking
                .builder()
                .vehicle(vehicle)
                .from(bookingDTO.getFrom())
                .to(bookingDTO.getTo())
                .build();
        bookingRepository.addBooking(booking);
        vehicle.getBookings().add(booking);
    }
}
