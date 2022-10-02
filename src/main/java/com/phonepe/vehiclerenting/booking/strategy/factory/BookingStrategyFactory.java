package com.phonepe.vehiclerenting.booking.strategy.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BookingStrategyFactory {

    @Autowired
    private Map<BookingStrategyType, BookingStrategy> bookingStrategyMap;

    public BookingStrategy getBookingStrategy(BookingStrategyType type) {
        return bookingStrategyMap.get(type);
    }
}
