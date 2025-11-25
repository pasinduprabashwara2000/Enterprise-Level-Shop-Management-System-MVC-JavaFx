package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ReturnProductModel;

public class ReturnProductController {

    private final ReturnProductModel returnProductModel = new ReturnProductModel();

    public String saveReturnProduct(ReturnProductDTO returnProductDTO) throws Exception {
        return returnProductModel.saveReturnProduct(returnProductDTO);
    }

    public String updateReturnProduct(ReturnProductDTO returnProductDTO) throws Exception {
        return returnProductModel.updateReturnProduct(returnProductDTO);
    }

    public String deleteReturnProduct(String returnItemId) throws Exception {
        return returnProductModel.deleteReturnProduct(returnItemId);
    }

    public ReturnProductDTO searchReturnProduct(String returnItemId) throws Exception {
        return returnProductModel.searchReturnProduct(returnItemId);
    }

    public ArrayList<ReturnProductDTO> getAllReturnProducts() throws Exception {
        return returnProductModel.getAllReturnProducts();
    }

}
