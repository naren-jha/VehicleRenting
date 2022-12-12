package com.njha.vehiclerenting.vehicle;

import com.njha.vehiclerenting.booking.Booking;
import com.njha.vehiclerenting.branch.Branch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private String number;
    private VehicleType vehicleType;
    private Branch branch;
    private List<Booking> bookings;

    public boolean isBookedForTimeDuration(LocalDateTime from, LocalDateTime to) {
        for (Booking booking : this.bookings) {
            if (booking.getFrom().isAfter(to) || booking.getTo().isBefore(from)) {
                continue;
            }
            return true;
        }

        return false;
    }
}
