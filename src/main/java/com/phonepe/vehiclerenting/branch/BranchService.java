package com.phonepe.vehiclerenting.branch;

import com.phonepe.vehiclerenting.vehicle.VehicleType;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.phonepe.vehiclerenting.common.Constants.BRANCH_CITY_NULL_ERR_MSG;
import static com.phonepe.vehiclerenting.common.Constants.BRANCH_NAME_NULL_ERR_MSG;

@Slf4j
@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public void addBranch(Branch branch) {
        validate(branch);
        branchRepository.addBranch(branch);
        log.info("Branch added: {}", branch);
    }

    public void allocatePrice(BranchPriceMapper branchPriceMapper) {
        branchRepository.addBranchPriceMapper(branchPriceMapper);
        log.info("Price allocated: {}", branchPriceMapper);
    }

    public BranchPriceMapper getAllocatedPrice(Branch branch, VehicleType vehicleType) {
        return branchRepository.getAllocatedPrice(branch, vehicleType);
    }

    private void validate(Branch branch) {
        if (Strings.isBlank(branch.getName())) {
            throw new IllegalArgumentException(BRANCH_NAME_NULL_ERR_MSG);
        }

        if (Strings.isBlank(branch.getCity())) {
            throw new IllegalArgumentException(BRANCH_CITY_NULL_ERR_MSG);
        }
    }
}
