package com.phonepe.vehiclerenting.booking;

import com.phonepe.vehiclerenting.booking.dto.BookingDTO;
import com.phonepe.vehiclerenting.booking.dto.BookingResponse;
import com.phonepe.vehiclerenting.branch.Branch;
import com.phonepe.vehiclerenting.branch.BranchPriceMapper;
import com.phonepe.vehiclerenting.branch.BranchService;
import com.phonepe.vehiclerenting.utility.DateTimeUtil;
import com.phonepe.vehiclerenting.vehicle.Vehicle;
import com.phonepe.vehiclerenting.vehicle.VehicleService;
import com.phonepe.vehiclerenting.vehicle.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private VehicleService vehicleService;

    @Test
    void shouldTestThatBookingIsDoneSuccessfully() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();
        branchService.addBranch(branch);

        BranchPriceMapper branchPriceMapper = BranchPriceMapper
                .builder()
                .branch(branch)
                .vehicleType(VehicleType.SEDAN)
                .price(100.0)
                .build();
        branchService.allocatePrice(branchPriceMapper);

        Vehicle vehicle = Vehicle
                .builder()
                .number("DL 01 MR 9310")
                .branch(branch)
                .vehicleType(VehicleType.SEDAN)
                .build();
        vehicleService.addVehicle(vehicle);

        BookingDTO bookingDTO1 = BookingDTO
                .builder()
                .vehicleType(VehicleType.SEDAN)
                .from(DateTimeUtil.getLocalDateTime("2020-02-29 10:00"))
                .to(DateTimeUtil.getLocalDateTime("2020-02-29 13:00"))
                .build();

        // when
        BookingResponse response = bookingService.bookVehicle(bookingDTO1);

        // then
        assertThat(response.getMessage()).isEqualTo("DL 01 MR 9310 from Vasanth Vihar booked.");
    }

}