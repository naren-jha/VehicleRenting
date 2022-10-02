package com.phonepe.vehiclerenting.booking.dto;

import com.phonepe.vehiclerenting.branch.Branch;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
    private String vehicleNumber;
    private Branch branch;
    private String message;
}
