package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PromotionDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class PromotionModel {

    public String savePromotion(PromotionDTO promotionDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Promotion (name, type, value, startAt, endAt, active, productID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, promotionDTO.getName());
        pstm.setString(2, promotionDTO.getType());
        pstm.setDouble(3, promotionDTO.getValue());
        pstm.setDate(4, promotionDTO.getStartAt());
        pstm.setDate(5, promotionDTO.getEndAt());
        pstm.setBoolean(6, promotionDTO.isActive());
        pstm.setString(7, promotionDTO.getProductID());

        return pstm.executeUpdate() > 0 ? "Promotion Saved Successfully" : "Promotion Save Failed";
    }

    public String updatePromotion(PromotionDTO promotionDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Promotion SET name=?, type=?, discount=?, startAt=?, endAt=?, active=?, categoryID=?, productID=? WHERE promoteID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, promotionDTO.getName());
        pstm.setString(2, promotionDTO.getType());
        pstm.setDouble(3, promotionDTO.getValue());
        pstm.setDate(4, promotionDTO.getStartAt());
        pstm.setDate(5, promotionDTO.getEndAt());
        pstm.setBoolean(6, promotionDTO.isActive());
        pstm.setString(7, promotionDTO.getProductID());
        pstm.setString(8, promotionDTO.getPromoteID());

        return pstm.executeUpdate() > 0 ? "Promotion Updated Successfully" : "Promotion Update Failed";
}

    public String deletePromotion(String promoteID) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Promotion WHERE promoteID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, promoteID);

        return pstm.executeUpdate() > 0 ? "Promotion Deleted Successfully" : "Promotion Delete Failed";
    }

    public PromotionDTO searchPromotion(String promoteID) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Promotion WHERE promoteID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, promoteID);

        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new PromotionDTO(
                    rst.getString("promoteID"),
                    rst.getString("name"),
                    rst.getString("type"),
                    rst.getDouble("value"),
                    rst.getDate("startAt"),
                    rst.getDate("endAt"),
                    rst.getBoolean("active"),
                    rst.getString("productID")
            );
        }        
        return null;
    }

    public ArrayList<PromotionDTO> getAllPromotions() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Promotion";
        PreparedStatement pstm = connection.prepareStatement(sql);
        var rst = pstm.executeQuery();
        ArrayList<PromotionDTO> promotionList = new ArrayList<>();
        while (rst.next()) {
            promotionList.add(new PromotionDTO(
                    rst.getString("promoteID"),
                    rst.getString("name"),
                    rst.getString("type"),
                    rst.getDouble("value"),
                    rst.getDate("startAt"),
                    rst.getDate("endAt"),
                    rst.getBoolean("active"),
                    rst.getString("productID")
            ));
        }
        return promotionList;
    }    
}