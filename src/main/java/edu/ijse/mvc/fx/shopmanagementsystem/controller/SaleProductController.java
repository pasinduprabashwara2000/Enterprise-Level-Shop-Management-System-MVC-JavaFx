package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SaleProductModel;

public class SaleProductController {

    private final SaleProductModel saleProductModel = new SaleProductModel();

    public String saveSaleProduct(SaleProductDTO saleProductDTO) throws Exception {
        return saleProductModel.saveSaleProduct(saleProductDTO);
    }

    public String updateSaleProduct(SaleProductDTO saleProductDTO) throws Exception {
        return saleProductModel.updateSaleProduct(saleProductDTO);
    }

    public String deleteSaleProduct(String saleProductID) throws Exception {
        return saleProductModel.deleteSaleProduct(saleProductID);
    }

    public SaleProductDTO searchSaleProduct(String saleProductID) throws Exception {
        return saleProductModel.searchSaleProduct(saleProductID);
    }

    public ArrayList<SaleProductDTO> getAllSaleProducts() throws Exception {
        return saleProductModel.getAllSaleProducts();
    }

}