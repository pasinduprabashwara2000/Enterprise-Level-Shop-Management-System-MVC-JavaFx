package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ReturnProductModel;

public class ReturnProductController {

    private final ReturnProductModel returnProductModel = new ReturnProductModel();

    public String saveReturnProduct(String returnItemId, String returnId, String productId, String saleItemId, int quantity, double refundAmount, edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO.Action action) throws Exception {
        return returnProductModel.saveReturnProduct(new edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO(returnItemId, returnId, productId, saleItemId, quantity, refundAmount, action));
    }

    public String updateReturnProduct(String returnItemId, String returnId, String productId, String saleItemId, int quantity, double refundAmount, edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO.Action action) throws Exception {
        return returnProductModel.updateReturnProduct(new edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO(returnItemId, returnId, productId, saleItemId, quantity, refundAmount, action));
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
