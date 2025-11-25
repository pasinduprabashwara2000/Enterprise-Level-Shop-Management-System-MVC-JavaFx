package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class ReturnModel {

    public String saveReturn(ReturnDTO returnDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Returns VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnDTO.getReturnID());
        pstm.setString(2, returnDTO.getSaleID());
        pstm.setString(3, returnDTO.getProcessedBy());
        pstm.setDate(4, Date.valueOf(returnDTO.getReturnDateTime()));
        pstm.setString(5, returnDTO.getReason());
        pstm.setObject(6, returnDTO.getStatus());

        return pstm.executeUpdate() > 0 ? "Return Processed Successfully" : "Return Processing Failed";

    }

    public String updateReturn(ReturnDTO returnDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Returns SET saleID=?, processedBy=?, returnDateTime=?, reason=?, status=? WHERE returnID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnDTO.getSaleID());
        pstm.setString(2, returnDTO.getProcessedBy());
        pstm.setDate(3, Date.valueOf(returnDTO.getReturnDateTime()));
        pstm.setString(4, returnDTO.getReason());
        pstm.setObject(5, returnDTO.getStatus());
        pstm.setString(6, returnDTO.getReturnID());

        return pstm.executeUpdate() > 0 ? "Return Updated Successfully" : "Return Update Failed";

    }

    public String deleteReturn(String returnID) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Returns WHERE returnID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnID);

        return pstm.executeUpdate() > 0 ? "Return Deleted Successfully" : "Return Delete Failed";

    }

    public ReturnDTO searchReturn(String returnID) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Returns WHERE returnID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnID);
        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new ReturnDTO(
                rst.getString("returnID"),
                rst.getString("saleID"),
                rst.getString("processedBy"),
                rst.getDate("returnDateTime").toLocalDate(),
                rst.getString("reason"),
                rst.getString("status")
            );
        }
        return null;
    }

    public ArrayList<ReturnDTO> getAllReturns() throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Returns";
        PreparedStatement pstm = connection.prepareStatement(sql);
        var rst = pstm.executeQuery();
        ArrayList<ReturnDTO> allReturns = new ArrayList<>();
        while (rst.next()) {
            allReturns.add(new ReturnDTO(
                rst.getString("returnID"),
                rst.getString("saleID"),
                rst.getString("processedBy"),
                rst.getDate("returnDateTime").toLocalDate(),
                rst.getString("reason"),
                rst.getString("status")
            ));
        }
        return allReturns;
    }
    
}
