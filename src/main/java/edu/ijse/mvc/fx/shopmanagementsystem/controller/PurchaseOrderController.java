package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaseOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PurchaseOrderModel;
import java.util.ArrayList;

public class PurchaseOrderController {

    final public PurchaseOrderModel purchaseOrderModel = new PurchaseOrderModel();

    public String savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception{
        return purchaseOrderModel.savePurchaseOrder(purchaseOrderDTO);
    }

    public String updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception {
        return purchaseOrderModel.updatePurchaseOrder(purchaseOrderDTO);
    }

    public String deletePurchaseOrder(String poid) throws Exception{
        return purchaseOrderModel.deletePurchaseOrder(poid);
    }

    public PurchaseOrderDTO searchPurchaseOrder(String id) throws Exception{
        return purchaseOrderModel.searchPurchaseOrder(id);
    }

    public ArrayList <PurchaseOrderDTO> getAllPurchaseOrder() throws Exception{
        return purchaseOrderModel.getAllPurchaseOrders();
    }

}
