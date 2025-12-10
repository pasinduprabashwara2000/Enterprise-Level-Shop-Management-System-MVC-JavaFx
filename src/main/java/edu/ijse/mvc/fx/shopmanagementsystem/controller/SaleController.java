package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SaleModel;
import java.util.ArrayList;

public class SaleController {

    final private SaleModel saleModel = new SaleModel();

    public String saveSale(SaleDTO saleDTO) throws Exception {
        return saleModel.saveSale(saleDTO);
    }

    public String updateSale(SaleDTO saleDTO) throws Exception {
        return saleModel.updateSale(saleDTO);
    }

    public String deleteSale(String saleId) throws Exception {
        return saleModel.deleteSale(saleId);
    }

    public SaleDTO searchSale(String saleId) throws Exception {
        return saleModel.searchSale(saleId);
    }

    public ArrayList<SaleDTO> getAllSales() throws Exception {
        return saleModel.getAllSales();
    }

}
