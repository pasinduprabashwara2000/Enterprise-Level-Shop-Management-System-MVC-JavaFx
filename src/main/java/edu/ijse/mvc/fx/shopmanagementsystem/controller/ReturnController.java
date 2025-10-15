package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ReturnModel;

public class ReturnController {

    private final ReturnModel returnModel = new ReturnModel();

    public String saveReturn(String returnID, String saleID, String processedBy, java.sql.Date returnDateTime, String reason, edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO.Status status) throws Exception {
        return returnModel.saveReturn(new edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO(returnID, saleID, processedBy, returnDateTime, reason, status));
    }

    public String updateReturn(String returnID, String saleID, String processedBy, java.sql.Date returnDateTime, String reason, edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO.Status status) throws Exception {
        return returnModel.updateReturn(new edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO(returnID, saleID, processedBy, returnDateTime, reason, status));
    }

    public String deleteReturn(String returnID) throws Exception {
        return returnModel.deleteReturn(returnID);
    }

    public ReturnDTO searchReturn(String returnID) throws Exception {
        return returnModel.searchReturn(returnID);
    }
    
    public ArrayList<ReturnDTO> getAllReturns() throws Exception {
        return returnModel.getAllReturns();
    }
    
}
