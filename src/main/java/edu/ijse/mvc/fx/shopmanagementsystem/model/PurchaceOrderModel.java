package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class PurchaceOrderModel {

    public String savePurchaceOrder(PurchaceOrderDTO purchaceOrderDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO PurchaceOrder (poId, supplierId, createdAt, createdBy, status, expectedDate, totalCost) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, purchaceOrderDTO.getPoId());
        pstm.setString(2, purchaceOrderDTO.getSupplierId());
        pstm.setDate(3, purchaceOrderDTO.getCreatedAt());
        pstm.setString(4, purchaceOrderDTO.getCreatedBy());
        pstm.setObject(5, purchaceOrderDTO.getStatus());
        pstm.setDate(6, purchaceOrderDTO.getExpectedDate());
        pstm.setDouble(7, purchaceOrderDTO.getTotalCost());

        return pstm.executeUpdate() > 0 ? "Purchase Order Saved Successfully" : "Purchase Order Save Failed";

    }

    public String updatePurchaceOrder(PurchaceOrderDTO purchaceOrderDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE PurchaceOrder SET supplierId=?, createdAt=?, createdBy=?, status=?, expectedDate=?, totalCost=? WHERE poId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, purchaceOrderDTO.getSupplierId());
        pstm.setDate(2, purchaceOrderDTO.getCreatedAt());
        pstm.setString(3, purchaceOrderDTO.getCreatedBy());
        pstm.setObject(4, purchaceOrderDTO.getStatus());
        pstm.setDate(5, purchaceOrderDTO.getExpectedDate());
        pstm.setDouble(6, purchaceOrderDTO.getTotalCost());
        pstm.setString(7, purchaceOrderDTO.getPoId());

        return pstm.executeUpdate() > 0 ? "Purchase Order Updated Successfully" : "Purchase Order Update Failed";

    }

    public String deletePurchaceOrder(String poId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM PurchaceOrder WHERE poId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, poId);

        return pstm.executeUpdate() > 0 ? "Purchase Order Deleted Successfully" : "Purchase Order Delete Failed";

    }

    public PurchaceOrderDTO searchPurchaceOrder(String poId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PurchaceOrder WHERE poId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, poId);

        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return new PurchaceOrderDTO(
                rst.getString("poId"),
                rst.getString("supplierId"),
                rst.getDate("createdAt"),
                rst.getString("createdBy"),
                (PurchaceOrderDTO.Status) rst.getObject("status"),
                rst.getDate("expectedDate"),
                rst.getDouble("totalCost")
            );
        }
        return null;
    }

    public ArrayList<PurchaceOrderDTO> getAllPurchaceOrders() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PurchaceOrder";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        ArrayList<PurchaceOrderDTO> allPurchaceOrders = new ArrayList<>();
        while (rst.next()) {
            allPurchaceOrders.add(new PurchaceOrderDTO(
                rst.getString("poId"),
                rst.getString("supplierId"),
                rst.getDate("createdAt"),
                rst.getString("createdBy"),
                (PurchaceOrderDTO.Status) rst.getObject("status"),
                rst.getDate("expectedDate"),
                rst.getDouble("totalCost")
            ));
        }
        return allPurchaceOrders;
    }
    
}
