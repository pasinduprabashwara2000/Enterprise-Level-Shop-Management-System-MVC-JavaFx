package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.InventoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.InventoryModel;

public class InventoryController {

    private final InventoryModel inventoryModel = new InventoryModel();

    public String saveInventory(InventoryDTO inventoryDTO) throws Exception {
        return inventoryModel.saveInventory(inventoryDTO);
    }

    public String updateInventory(InventoryDTO inventoryDTO) throws Exception {
        return inventoryModel.updateInventory(inventoryDTO);
    }

    public String deleteInventory(String productID) throws Exception {
        return inventoryModel.deleteInventory(productID);
    }

    public InventoryDTO searchInventory(String productID) throws Exception {
        return inventoryModel.searchInventory(productID);
    }

    public ArrayList<InventoryDTO> getAllInventories() throws Exception {
        return inventoryModel.getAllInventories();
    }
    
}
