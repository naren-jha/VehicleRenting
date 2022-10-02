package com.phonepe.vehiclerenting.branch;

import com.phonepe.vehiclerenting.Database;
import com.phonepe.vehiclerenting.vehicle.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BranchRepository {

    @Autowired
    private Database db;

    public void addBranch(Branch branch) {
        db.addBranch(branch);
    }

    public void addBranchPriceMapper(BranchPriceMapper branchPriceMapper) {
        db.addBranchPriceMapper(branchPriceMapper);
    }

    public BranchPriceMapper getAllocatedPrice(Branch branch, VehicleType vehicleType) {
        List<BranchPriceMapper> branchPriceMappers = new ArrayList<>(db.getBranchPriceMappers().values());
        for (BranchPriceMapper branchPriceMapper : branchPriceMappers) {
            if (branchPriceMapper.getBranch().equals(branch) && branchPriceMapper.getVehicleType().equals(vehicleType)) {
                return branchPriceMapper;
            }
        }
        return null;
    }
}
