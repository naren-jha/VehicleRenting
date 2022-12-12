package com.njha.vehiclerenting.vehicle;

import com.njha.vehiclerenting.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VehicleRepository {

    @Autowired
    private Database db;

    public void addVehicle(Vehicle vehicle) {
        db.addVehicle(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(db.getVehicles().values());
    }

    public List<Vehicle> getVehiclesByType(VehicleType vehicleType) {
        List<Vehicle> vehicles = getAllVehicles();
        return vehicles.stream()
                .filter(vehicle -> vehicle.getVehicleType().equals(vehicleType))
                .collect(Collectors.toList());
    }

    public Optional<Vehicle> getVehicle(Long id) {
        return Optional.ofNullable(db.getVehicles().get(id));
    }
}
