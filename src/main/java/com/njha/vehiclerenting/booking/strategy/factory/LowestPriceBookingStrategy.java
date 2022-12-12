package com.njha.vehiclerenting.booking.strategy.factory;

import com.njha.vehiclerenting.booking.dto.BookingDTO;
import com.njha.vehiclerenting.branch.BranchPriceMapper;
import com.njha.vehiclerenting.branch.BranchService;
import com.njha.vehiclerenting.vehicle.Vehicle;
import com.njha.vehiclerenting.vehicle.VehicleService;
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
    public Vehicle selectVehicle(BookingDTO bookingDTO) {
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
