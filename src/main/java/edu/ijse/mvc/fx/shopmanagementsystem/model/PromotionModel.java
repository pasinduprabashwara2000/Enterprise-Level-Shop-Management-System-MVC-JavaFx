package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PromotionDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class PromotionModel {

    public String savePromotion(PromotionDTO promotionDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Promotion (promoteID, name, type, discount, startAt, endAt, active, categoryID, productID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, promotionDTO.getPromoteID());
        pstm.setString(2, promotionDTO.getName());
        pstm.setString(3, promotionDTO.getType().name());
        pstm.setDouble(4, promotionDTO.getValue());
        pstm.setDate(5, new java.sql.Date(promotionDTO.getStartAt().getTime()));
        pstm.setDate(6, new java.sql.Date(promotionDTO.getEndAt().getTime()));
        pstm.setBoolean(7, promotionDTO.isActive());
        pstm.setString(8, promotionDTO.getCategoryID());
        pstm.setString(9, promotionDTO.getProductID());

        return pstm.executeUpdate() > 0 ? "Promotion Saved Successfully" : "Promotion Save Failed";
    }

    public String updatePromotion(PromotionDTO promotionDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Promotion SET name=?, type=?, discount=?, startAt=?, endAt=?, active=?, categoryID=?, productID=? WHERE promoteID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, promotionDTO.getName());
        pstm.setString(2, promotionDTO.getType().name());
        pstm.setDouble(3, promotionDTO.getValue());
        pstm.setDate(4, new java.sql.Date(promotionDTO.getStartAt().getTime()));
        pstm.setDate(5, new java.sql.Date(promotionDTO.getEndAt().getTime()));
        pstm.setBoolean(6, promotionDTO.isActive());
        pstm.setString(7, promotionDTO.getCategoryID());
        pstm.setString(8, promotionDTO.getProductID());
        pstm.setString(9, promotionDTO.getPromoteID());

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
                    PromotionDTO.Type.valueOf(rst.getString("type")),
                    rst.getDouble("value"),
                    rst.getDate("startAt"),
                    rst.getDate("endAt"),
                    rst.getBoolean("active"),
                    rst.getString("categoryID"),
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
                    PromotionDTO.Type.valueOf(rst.getString("type")),
                    rst.getDouble("value"),
                    rst.getDate("startAt"),
                    rst.getDate("endAt"),
                    rst.getBoolean("active"),
                    rst.getString("categoryID"),
                    rst.getString("productID")
            ));
        }
        return promotionList;
    }    
}