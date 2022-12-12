package com.njha.vehiclerenting;

import com.njha.vehiclerenting.booking.Booking;
import com.njha.vehiclerenting.branch.Branch;
import com.njha.vehiclerenting.vehicle.Vehicle;
import com.njha.vehiclerenting.branch.BranchPriceMapper;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class Database {
    Map<Long, Branch> branches = new HashMap<>();
    Map<Long, Vehicle> vehicles = new HashMap<>();
    Map<Long, BranchPriceMapper> branchPriceMappers = new HashMap<>();
    Map<Long, Booking> bookings = new HashMap<>();

    public void addBranch(Branch branch) {
        branch.setId(Long.valueOf(branches.size()));
        branches.put(branch.getId(), branch);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicle.setId(Long.valueOf(vehicles.size()));
        vehicles.put(vehicle.getId(), vehicle);
    }

    public void addBranchPriceMapper(BranchPriceMapper branchPriceMapper) {
        branchPriceMapper.setId(Long.valueOf(branchPriceMappers.size()));
        branchPriceMappers.put(branchPriceMapper.getId(), branchPriceMapper);
    }

    public void addBooking(Booking booking) {
        booking.setId(Long.valueOf(bookings.size()));
        bookings.put(booking.getId(), booking);
    }

}
