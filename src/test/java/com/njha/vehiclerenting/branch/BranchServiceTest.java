package com.njha.vehiclerenting.branch;

import com.njha.vehiclerenting.common.Constants;
import com.njha.vehiclerenting.vehicle.VehicleType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
class BranchServiceTest {

    @Autowired
    private BranchService branchService;

    @Test
    void shouldTestThatBranchIsAddedSuccessfully() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();
        // when
        branchService.addBranch(branch);
        // then
        assertThat(branch.getId()).isNotNull();
    }

    @Test
    void shouldTestThatBranchIsNotAddedDueToEmptyBranchName() {
        // given
        Branch branch = Branch
                .builder()
                .name(" ")
                .city("Delhi")
                .build();

        // when
        Throwable thrown = catchThrowable(() -> {
            branchService.addBranch(branch);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Constants.BRANCH_NAME_NULL_ERR_MSG);
    }

    @Test
    void shouldTestThatBranchIsNotAddedDueToEmptyCityName() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city(" ")
                .build();

        // when
        Throwable thrown = catchThrowable(() -> {
            branchService.addBranch(branch);
        });

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Constants.BRANCH_CITY_NULL_ERR_MSG);
    }

    @Test
    void shouldTestThatPriceIsAllocatedSuccessfully() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();
        BranchPriceMapper actual = BranchPriceMapper
                .builder()
                .branch(branch)
                .vehicleType(VehicleType.SEDAN)
                .price(100.0)
                .build();

        // when
        branchService.allocatePrice(actual);

        // then
        BranchPriceMapper expected = branchService.getAllocatedPrice(branch, VehicleType.SEDAN);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void shouldTestThatAllocatedPriceIsNotFoundForIncorrectInput() {
        // given
        Branch branch = Branch
                .builder()
                .name("Vasanth Vihar")
                .city("Delhi")
                .build();

        // when
        BranchPriceMapper expected = branchService.getAllocatedPrice(branch, VehicleType.SEDAN);

        // then
        assertThat(expected).isEqualTo(null);
    }
}