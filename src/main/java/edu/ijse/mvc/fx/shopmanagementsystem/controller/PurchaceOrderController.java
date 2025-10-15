package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PurchaceOrderModel;

public class PurchaceOrderController {

    private final PurchaceOrderModel purchaceOrderModel = new PurchaceOrderModel();

    public String savePurchaceOrder(PurchaceOrderDTO purchaceOrderDTO) throws Exception {
        return purchaceOrderModel.savePurchaceOrder(purchaceOrderDTO);
    }

    public String updatePurchaceOrder(PurchaceOrderDTO purchaceOrderDTO) throws Exception {
        return purchaceOrderModel.updatePurchaceOrder(purchaceOrderDTO);
    }

    public String deletePurchaceOrder(String poId) throws Exception {
        return purchaceOrderModel.deletePurchaceOrder(poId);
    }

    public PurchaceOrderDTO searchPurchaceOrder(String poId) throws Exception {
        return purchaceOrderModel.searchPurchaceOrder(poId);
    }

    public ArrayList<PurchaceOrderDTO> getAllPurchaceOrders() throws Exception {
        return purchaceOrderModel.getAllPurchaceOrders();
    }
    
}
