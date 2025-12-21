package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ReturnModel;

import java.util.ArrayList;

public class ReturnController {

    private final ReturnModel returnModel = new ReturnModel();

    public String saveReturn(edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO dto) throws Exception {
        return returnModel.saveReturn(dto);
    }

    public String updateReturn(edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO dto) throws Exception {
        return returnModel.updateReturn(dto);
    }

    public String deleteReturn(String returnID) throws Exception {
        return returnModel.deleteReturn(returnID);
    }

    public ReturnDTO searchReturn(String returnID) throws Exception {
        return returnModel.searchReturn(returnID);
    }

    public ArrayList<ReturnDTO> getAllReturn() throws Exception{
        return returnModel.getAllReturns();
    }

    
}
