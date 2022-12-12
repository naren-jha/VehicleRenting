package com.njha.vehiclerenting.booking.dto;

import com.njha.vehiclerenting.branch.Branch;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
    private String vehicleNumber;
    private Branch branch;
    private String message;
}
