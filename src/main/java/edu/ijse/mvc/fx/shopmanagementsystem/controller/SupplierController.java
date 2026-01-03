package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplierDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SupplierModel;

public class SupplierController {

    private final SupplierModel supplierModel = new SupplierModel();
    
    public String saveSupplier(SupplierDTO supplierDTO) throws Exception {
        return supplierModel.saveSupplier(supplierDTO);
    }

    public String updateSupplier(SupplierDTO supplierDTO) throws Exception {
        return supplierModel.updateSupplier(supplierDTO);
    }

    public String deleteSupplier(String supplierID) throws Exception {
        return supplierModel.deleteSupplier(supplierID);
    }

    public SupplierDTO searchSupplier(String supplierID) throws Exception {
        return supplierModel.searchSupplier(supplierID);
    }

    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception {
        return supplierModel.getAllSuppliers();
    }

}
