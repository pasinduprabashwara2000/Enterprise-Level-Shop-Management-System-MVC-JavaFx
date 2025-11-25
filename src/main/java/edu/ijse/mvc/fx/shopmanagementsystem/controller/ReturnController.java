package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ReturnModel;

public class ReturnController {

    private final ReturnModel returnModel = new ReturnModel();

    public String saveReturn(ReturnDTO returnDTO) throws Exception {
        return returnModel.saveReturn(returnDTO);
    }

    public String updateReturn(ReturnDTO returnDTO) throws Exception {
        return returnModel.updateReturn(returnDTO);
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
