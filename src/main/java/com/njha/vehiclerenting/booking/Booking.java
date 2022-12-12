package com.njha.vehiclerenting.booking;

import com.njha.vehiclerenting.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long id;
    private Vehicle vehicle;
    private LocalDateTime from;
    private LocalDateTime to;
}
