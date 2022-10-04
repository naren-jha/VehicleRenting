package com.phonepe.vehiclerenting.booking.strategy.factory;

import com.phonepe.vehiclerenting.booking.BookingService;
import com.phonepe.vehiclerenting.booking.dto.BookingDTO;
import com.phonepe.vehiclerenting.booking.dto.BookingResponse;
import com.phonepe.vehiclerenting.branch.BranchPriceMapper;
import com.phonepe.vehiclerenting.branch.BranchService;
import com.phonepe.vehiclerenting.vehicle.Vehicle;
import com.phonepe.vehiclerenting.vehicle.VehicleService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Component
@Data
public class LowestPriceBookingStrategy implements BookingStrategy {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private BranchService branchService;

    @Override
    public Vehicle selectVehicle(BookingService bookingService, BookingDTO bookingDTO) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByType(bookingDTO.getVehicleType());
        SortedMap<Double, Vehicle> priceVehicleMap = new TreeMap<>();
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isBookedForTimeDuration(bookingDTO.getFrom(), bookingDTO.getTo())) {
                BranchPriceMapper priceMapper = branchService.getAllocatedPrice(vehicle.getBranch(), vehicle.getVehicleType());
                priceVehicleMap.put(priceMapper.getPrice(), vehicle);
            }
        }

        return priceVehicleMap.size() > 0 ? priceVehicleMap.entrySet().iterator().next().getValue() : null;
    }

    @Override
    public BookingStrategyType bookingStrategyType() {
        return BookingStrategyType.LOWEST_PRICE;
    }
}
