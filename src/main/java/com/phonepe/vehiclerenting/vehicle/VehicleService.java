package com.phonepe.vehiclerenting.vehicle;

import com.phonepe.vehiclerenting.branch.Branch;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.phonepe.vehiclerenting.common.Constants.*;

@Slf4j
@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public void addVehicle(Vehicle vehicle) {
        validate(vehicle);
        vehicle.setBookings(new ArrayList<>());
        vehicleRepository.addVehicle(vehicle);
        log.info("Vehicle added: {}", vehicle);
    }

    public Vehicle getVehicle(Long id) {
        return vehicleRepository.getVehicle(id)
                .orElseThrow(() -> new IllegalStateException("Vehicle does not exist!"));
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.getAllVehicles();
    }

    public List<Vehicle> getVehiclesByType(VehicleType vehicleType) {
        if (Objects.isNull(vehicleType)) {
            throw new IllegalArgumentException(VEHICLE_TYPE_NULL_ERR_MSG);
        }

        return vehicleRepository.getVehiclesByType(vehicleType);
    }

    public List<String> viewInventory(LocalDateTime from, LocalDateTime to) {
        List<Vehicle> vehicles = getAllVehicles();
        Map<Branch, List<Vehicle>> branchVehicleMap = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            List<Vehicle> branchVehicles = branchVehicleMap.getOrDefault(vehicle.getBranch(), new ArrayList<>());
            branchVehicles.add(vehicle);
            branchVehicleMap.put(vehicle.getBranch(), branchVehicles);
        }

        List<String> response = new ArrayList<>();
        for (Map.Entry<Branch, List<Vehicle>> entry : branchVehicleMap.entrySet()) {
            response.add(String.format("Branch: %s", entry.getKey().getName()));
            for (Vehicle vehicle : entry.getValue()) {
                String status = vehicle.isBookedForTimeDuration(from, to) ? "Booked" : "Available";
                response.add(String.format("%s %s %s", vehicle.getVehicleType().toString(), vehicle.getNumber(), status));
            }
        }

        return response;
    }

    private void validate(Vehicle vehicle) {
        if (Strings.isBlank(vehicle.getNumber())) {
            throw new IllegalArgumentException(VEHICLE_NUMBER_NULL_ERR_MSG);
        }

        if (Objects.isNull(vehicle.getBranch())) {
            throw new IllegalArgumentException(VEHICLE_BRANCH_NULL_ERR_MSG);
        }

        if (Objects.isNull(vehicle.getVehicleType())) {
            throw new IllegalArgumentException(VEHICLE_TYPE_NULL_ERR_MSG);
        }
    }
}
