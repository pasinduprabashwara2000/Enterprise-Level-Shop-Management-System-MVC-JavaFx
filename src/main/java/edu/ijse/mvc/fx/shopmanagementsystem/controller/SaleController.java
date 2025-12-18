package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.model.SaleModel;
import java.util.ArrayList;

public class SaleController {

    final private SaleModel saleModel = new SaleModel();

    public String saveSale(SaleDTO saleDTO) throws Exception {
        return saleModel.save(saleDTO);
    }

    public String updateSale(SaleDTO saleDTO) throws Exception {
        return saleModel.update(saleDTO);
    }

    public String deleteSale(Integer saleId) throws Exception {
        return saleModel.delete(saleId);
    }

    public SaleDTO searchSale(Integer saleId) throws Exception {
        return saleModel.search(saleId);
    }

    public ArrayList <SaleProductTM> getAllSale() throws Exception{
        return saleModel.getAll();
    }

}
