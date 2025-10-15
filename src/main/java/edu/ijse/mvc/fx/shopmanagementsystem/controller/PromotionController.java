package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PromotionDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PromotionModel;

public class PromotionController {

    private final PromotionModel promotionModel = new PromotionModel();

    public String savePromotion(PromotionDTO promotionDTO) throws Exception{
        return promotionModel.savePromotion(promotionDTO);
    }

    public String updatePromotion(PromotionDTO promotionDTO) throws Exception{
        return promotionModel.updatePromotion(promotionDTO);
    }

    public String deletePromotion(String promoteID) throws Exception{
        return promotionModel.deletePromotion(promoteID);
    }

    public PromotionDTO searchPromotion(String promoteID) throws Exception{
        return promotionModel.searchPromotion(promoteID);
    }

    public ArrayList<PromotionDTO> getAllPromotions() throws Exception{
        return promotionModel.getAllPromotions();
    }
    
}