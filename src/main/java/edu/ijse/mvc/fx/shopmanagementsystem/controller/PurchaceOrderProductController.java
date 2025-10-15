package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PurchaceOrderProductModel;

public class PurchaceOrderProductController {

    private final PurchaceOrderProductModel purchaceOrderProductModel = new PurchaceOrderProductModel();

    public String savePurchaceOrderProduct(edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderProductDTO purchaceOrderProductDTO) throws Exception {
        return purchaceOrderProductModel.savePurchaceOrderProduct(purchaceOrderProductDTO);
    }

    public String updatePurchaceOrderProduct(edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderProductDTO purchaceOrderProductDTO) throws Exception {
        return purchaceOrderProductModel.updatePurchaceOrderProduct(purchaceOrderProductDTO);
    }

    public String deletePurchaceOrderProduct(String poItemId) throws Exception {
        return purchaceOrderProductModel.deletePurchaceOrderProduct(poItemId);
    }

    public PurchaceOrderProductDTO searchPurchaceOrderProduct(String poItemId) throws Exception {
        return purchaceOrderProductModel.searchPurchaceOrderProduct(poItemId);
    }

    public ArrayList<PurchaceOrderProductDTO> getAllPurchaceOrderProducts() throws Exception {
        return purchaceOrderProductModel.getAllPurchaceOrderProducts();
    }
    
}
