package com.phonepe.vehiclerenting.branch;

import com.phonepe.vehiclerenting.vehicle.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchPriceMapper {
    private Long id;
    private Branch branch;
    private VehicleType vehicleType;
    private Double price;
}
