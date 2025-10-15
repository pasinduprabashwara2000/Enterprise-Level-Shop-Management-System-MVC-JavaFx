package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SaleModel;

public class SaleController {

    private final SaleModel saleModel = new SaleModel();

    public String saveModel(SaleDTO saleDTO) throws Exception {
        return saleModel.saveSale(saleDTO);
    }

    public String updateModel(SaleDTO saleDTO) throws Exception {
        return saleModel.updateSale(saleDTO);
    }

    public String deleteModel(String saleID) throws Exception {
        return saleModel.deleteSale(saleID);
    }

    public SaleDTO searchModel(String saleID) throws Exception {
        return saleModel.searchSale(saleID);
    }

    public ArrayList<SaleDTO> getAllModel() throws Exception {
        return saleModel.getAllSales();
    }
    
}
