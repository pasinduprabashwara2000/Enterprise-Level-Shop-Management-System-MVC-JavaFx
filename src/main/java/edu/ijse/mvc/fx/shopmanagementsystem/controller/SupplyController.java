package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplyDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SupplyModel;

public class SupplyController {

    private final SupplyModel supplyModel = new SupplyModel();

    public String saveSupply(SupplyDTO supplyDTO) throws Exception {
        return supplyModel.saveSupply(supplyDTO);
    }

    public String updateSupply(SupplyDTO supplyDTO) throws Exception {
        return supplyModel.updateSupply(supplyDTO);
    }

    public String deleteSupply(String productID, String supplyID) throws Exception {
        return supplyModel.deleteSupply(productID, supplyID);
    }

    public SupplyDTO searchSupply(String productID, String supplyID) throws Exception {
        return supplyModel.searchSupply(productID, supplyID);
    }

    public ArrayList<SupplyDTO> getAllSupplies() throws Exception {
        return supplyModel.getAllSupplies();
    }

}
