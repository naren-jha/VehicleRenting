package com.phonepe.vehiclerenting.vehicle;

import com.phonepe.vehiclerenting.branch.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.phonepe.vehiclerenting.common.Constants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @Test
    void shouldTestThatVehicleIsAddedSuccessfully() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();

        Vehicle actual = Vehicle
                .builder()
                .number("DL 01 MR 9310")
                .branch(branch)
                .vehicleType(VehicleType.SEDAN)
                .build();

        // when
        vehicleService.addVehicle(actual);

        // then
        Vehicle expected = vehicleService.getVehicle(actual.getId());
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void shouldTestThatVehicleIsNotAddedDueToNullVehicleNumber() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();

        Vehicle vehicle = Vehicle
                .builder()
                .number(null)
                .branch(branch)
                .vehicleType(VehicleType.SEDAN)
                .build();

        // when
        Throwable thrown = catchThrowable(() -> {
            vehicleService.addVehicle(vehicle);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VEHICLE_NUMBER_NULL_ERR_MSG);
    }

    @Test
    void shouldTestThatVehicleIsNotAddedDueToNullBranch() {
        // given
        Vehicle vehicle = Vehicle
                .builder()
                .number("DL 01 MR 9310")
                .branch(null)
                .vehicleType(VehicleType.SEDAN)
                .build();

        // when
        Throwable thrown = catchThrowable(() -> {
            vehicleService.addVehicle(vehicle);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VEHICLE_BRANCH_NULL_ERR_MSG);
    }

    @Test
    void shouldTestThatVehicleIsNotAddedDueToNullVehicleType() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();

        Vehicle vehicle = Vehicle
                .builder()
                .number("DL 01 MR 9310")
                .branch(branch)
                .vehicleType(null)
                .build();

        // when
        Throwable thrown = catchThrowable(() -> {
            vehicleService.addVehicle(vehicle);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VEHICLE_TYPE_NULL_ERR_MSG);
    }

    @Test
    void shouldTestGetVehiclesByTypeSuccess() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();

        Vehicle vehicle = Vehicle
                .builder()
                .number("DL 01 MR 9310")
                .branch(branch)
                .vehicleType(VehicleType.SEDAN)
                .build();
        vehicleService.addVehicle(vehicle);

        // when
        List<Vehicle> vehicles = vehicleService.getVehiclesByType(VehicleType.SEDAN);

        // then
        assertThat(vehicles.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void shouldTestGetVehiclesByTypeFailure() {
        // given
        VehicleType vehicleType = null;

        // when
        Throwable thrown = catchThrowable(() -> {
            vehicleService.getVehiclesByType(vehicleType);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VEHICLE_TYPE_NULL_ERR_MSG);
    }
}