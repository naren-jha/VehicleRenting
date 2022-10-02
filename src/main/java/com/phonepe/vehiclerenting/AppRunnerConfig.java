package com.phonepe.vehiclerenting;

import com.phonepe.vehiclerenting.booking.BookingService;
import com.phonepe.vehiclerenting.booking.dto.BookingDTO;
import com.phonepe.vehiclerenting.booking.dto.BookingResponse;
import com.phonepe.vehiclerenting.branch.Branch;
import com.phonepe.vehiclerenting.branch.BranchPriceMapper;
import com.phonepe.vehiclerenting.branch.BranchService;
import com.phonepe.vehiclerenting.utility.DateTimeUtil;
import com.phonepe.vehiclerenting.vehicle.Vehicle;
import com.phonepe.vehiclerenting.vehicle.VehicleService;
import com.phonepe.vehiclerenting.vehicle.VehicleType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Configuration
@AllArgsConstructor
public class AppRunnerConfig {

    private BranchService branchService;
    private VehicleService vehicleService;
    private BookingService bookingService;

    @Bean
    CommandLineRunner commandLineRunner() {
        Branch branch1 = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();
        Branch branch2 = Branch
                .builder()
                .name("Cyber City")
                .city("Delhi")
                .build();

        BranchPriceMapper branchPriceMapper1 = BranchPriceMapper
                .builder()
                .branch(branch1)
                .vehicleType(VehicleType.SEDAN)
                .price(100.0)
                .build();
        BranchPriceMapper branchPriceMapper2 = BranchPriceMapper
                .builder()
                .branch(branch1)
                .vehicleType(VehicleType.HATCHBACK)
                .price(800.0)
                .build();
        BranchPriceMapper branchPriceMapper3 = BranchPriceMapper
                .builder()
                .branch(branch2)
                .vehicleType(VehicleType.SEDAN)
                .price(200.0)
                .build();
        BranchPriceMapper branchPriceMapper4 = BranchPriceMapper
                .builder()
                .branch(branch2)
                .vehicleType(VehicleType.HATCHBACK)
                .price(50.0)
                .build();

        Vehicle vehicle1 = Vehicle
                .builder()
                .number("DL 01 MR 9310")
                .branch(branch1)
                .vehicleType(VehicleType.SEDAN)
                .build();
        Vehicle vehicle2 = Vehicle
                .builder()
                .number("DL 01 MR 9311")
                .branch(branch2)
                .vehicleType(VehicleType.SEDAN)
                .build();
        Vehicle vehicle3 = Vehicle
                .builder()
                .number("DL 01 MR 9312")
                .branch(branch2)
                .vehicleType(VehicleType.HATCHBACK)
                .build();

        BookingDTO bookingDTO1 = BookingDTO
                .builder()
                .vehicleType(VehicleType.SEDAN)
                .from(DateTimeUtil.getLocalDateTime("2020-02-29 10:00"))
                .to(DateTimeUtil.getLocalDateTime("2020-02-29 13:00"))
                .build();
        BookingDTO bookingDTO2 = BookingDTO
                .builder()
                .vehicleType(VehicleType.SEDAN)
                .from(DateTimeUtil.getLocalDateTime("2020-02-29 14:00"))
                .to(DateTimeUtil.getLocalDateTime("2020-02-29 15:00"))
                .build();

        return args -> {
            // add branches
            log.info("===========================================");
            branchService.addBranch(branch1);
            branchService.addBranch(branch2);

            // allocate prices
            log.info("===========================================");
            branchService.allocatePrice(branchPriceMapper1);
            branchService.allocatePrice(branchPriceMapper2);
            branchService.allocatePrice(branchPriceMapper3);
            branchService.allocatePrice(branchPriceMapper4);

            // add vehicles
            log.info("===========================================");
            vehicleService.addVehicle(vehicle1);
            vehicleService.addVehicle(vehicle2);
            vehicleService.addVehicle(vehicle3);

            // bookings
            BookingResponse response = bookingService.bookVehicle(bookingDTO1);
            log.info("===========================================");
            log.info(response.getMessage());

            response = bookingService.bookVehicle(bookingDTO2);
            log.info("===========================================");
            log.info(response.getMessage());

            response = bookingService.bookVehicle(bookingDTO2);
            log.info("===========================================");
            log.info(response.getMessage());

            response = bookingService.bookVehicle(bookingDTO2);
            log.info("===========================================");
            log.info(response.getMessage());

            // view inventory
            LocalDateTime from = DateTimeUtil.getLocalDateTime("2020-02-29 14:00");
            LocalDateTime to = DateTimeUtil.getLocalDateTime("2020-02-29 15:00");
            List<String> invResponse = vehicleService.viewInventory(from, to);
            printList(invResponse);

            from = DateTimeUtil.getLocalDateTime("2020-02-29 16:00");
            to = DateTimeUtil.getLocalDateTime("2020-02-29 17:00");
            invResponse = vehicleService.viewInventory(from, to);
            printList(invResponse);
        };
    }

    private void printList(List<String> invResponse) {
        log.info("===========================================");
        for (String res : invResponse) {
            log.info(res);
        }
    }
}
