package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReturnModel {

    public String saveReturn(ReturnDTO returnDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Returns (customerId, action, reason, refundAmount, returnDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnDTO.getCustomerId());
        pstm.setString(2, returnDTO.getAction());
        pstm.setString(3, returnDTO.getReason());
        pstm.setDouble(4, returnDTO.getRefundAmount());
        pstm.setDate(5, java.sql.Date.valueOf(returnDTO.getReturnDate()));
        pstm.setString(6, returnDTO.getStatus());

        return pstm.executeUpdate() > 0 ? "Return saved successfully." : "Failed to save return.";
    }

    public String updateReturn(ReturnDTO returnDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Returns SET customerId = ?, action = ?, reason = ?, refundAmount = ?, returnDate = ?, status = ? WHERE returnId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnDTO.getCustomerId());
        pstm.setString(2, returnDTO.getAction());
        pstm.setString(3, returnDTO.getReason());
        pstm.setDouble(4, returnDTO.getRefundAmount());
        pstm.setDate(5, java.sql.Date.valueOf(returnDTO.getReturnDate()));
        pstm.setString(6, returnDTO.getStatus());
        pstm.setString(7, returnDTO.getReturnId());

        return pstm.executeUpdate() > 0 ? "Return updated successfully." : "Failed to update return.";
    }

    public String deleteReturn(String returnId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Returns WHERE returnId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnId);

        return pstm.executeUpdate() > 0 ? "Return deleted successfully." : "Failed to delete return.";
    }

    public ReturnDTO searchReturn(String returnId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Returns WHERE returnId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnId);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return new ReturnDTO(
                    resultSet.getString("returnId"),
                    resultSet.getString("customerId"),
                    resultSet.getDouble("refundAmount"),
                    resultSet.getString("reason"),
                    resultSet.getString("action"),
                    resultSet.getString("status"),
                    resultSet.getDate("returnDate").toLocalDate()
            );
        }
        return null;
    }

    public ArrayList<ReturnDTO> getAllReturns() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Returns";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<ReturnDTO> returnList = new ArrayList<>();
        while (resultSet.next()) {
            ReturnDTO returnDTO = new ReturnDTO(
                    resultSet.getString("returnId"),
                    resultSet.getString("customerId"),
                    resultSet.getDouble("refundAmount"),
                    resultSet.getString("reason"),
                    resultSet.getString("action"),
                    resultSet.getString("status"),
                    resultSet.getDate("returnDate").toLocalDate()
            );
            returnList.add(returnDTO);
        }
        return returnList;
    }

}
