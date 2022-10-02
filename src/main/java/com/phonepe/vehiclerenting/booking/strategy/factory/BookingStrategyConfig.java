package com.phonepe.vehiclerenting.booking.strategy.factory;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class BookingStrategyConfig {

    private final List<BookingStrategy> bookingStrategies;

    @Bean
    public Map<BookingStrategyType, BookingStrategy> bookingStrategyMap() {
        Map<BookingStrategyType, BookingStrategy> bookingStrategyMap = new EnumMap<>(BookingStrategyType.class);
        bookingStrategies.forEach(bookingStrategy -> bookingStrategyMap.put(bookingStrategy.bookingStrategyType(), bookingStrategy));
        return bookingStrategyMap;
    }
}