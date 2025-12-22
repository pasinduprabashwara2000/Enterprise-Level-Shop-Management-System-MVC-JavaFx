package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.PurchaceOrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class PurchaceOrderModel {

    public String savePurchaceOrder(PurchaceOrderDTO purchaceOrderDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO PurchaseOrder (supplierId, createdAt, createdBy, status, expectedDate, totalCost) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, purchaceOrderDTO.getSupplierId());
        pstm.setDate(2, Date.valueOf(purchaceOrderDTO.getCreatedAt()));
        pstm.setString(3, purchaceOrderDTO.getCreatedBy());
        pstm.setObject(4, purchaceOrderDTO.getStatus());
        pstm.setDate(5, Date.valueOf(purchaceOrderDTO.getExpectedDate()));
        pstm.setDouble(6, purchaceOrderDTO.getTotalCost());

        return pstm.executeUpdate() > 0 ? "Purchase Order Saved Successfully" : "Purchase Order Save Failed";

    }

    public String updatePurchaceOrder(PurchaceOrderDTO purchaceOrderDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE PurchaseOrder SET supplierId=?, createdAt=?, createdBy=?, status=?, expectedDate=?, totalCost=? WHERE poId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, purchaceOrderDTO.getSupplierId());
        pstm.setDate(2, Date.valueOf(purchaceOrderDTO.getCreatedAt()));
        pstm.setString(3, purchaceOrderDTO.getCreatedBy());
        pstm.setObject(4, purchaceOrderDTO.getStatus());
        pstm.setDate(5, Date.valueOf(purchaceOrderDTO.getExpectedDate()));
        pstm.setDouble(6, purchaceOrderDTO.getTotalCost());
        pstm.setString(7, purchaceOrderDTO.getPoId());

        return pstm.executeUpdate() > 0 ? "Purchase Order Updated Successfully" : "Purchase Order Update Failed";

    }

    public String deletePurchaceOrder(String poId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM PurchaseOrder WHERE poId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, poId);

        return pstm.executeUpdate() > 0 ? "Purchase Order Deleted Successfully" : "Purchase Order Delete Failed";

    }

    public PurchaceOrderDTO searchPurchaceOrder(String poId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PurchaseOrder WHERE poId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, poId);

        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return new PurchaceOrderDTO(
                rst.getString("poId"),
                rst.getString("supplierId"),
                rst.getDate("createdAt").toLocalDate(),
                rst.getString("createdBy"),
                rst.getString("status"),
                rst.getDate("expectedDate").toLocalDate(),
                rst.getDouble("totalCost")
            );
        }
        return null;
    }

    public ArrayList<PurchaceOrderDTO> getAllPurchaceOrders() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PurchaseOrder";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        ArrayList<PurchaceOrderDTO> allPurchaceOrders = new ArrayList<>();
        while (rst.next()) {
            allPurchaceOrders.add(new PurchaceOrderDTO(
                rst.getString("poId"),
                rst.getString("supplierId"),
                rst.getDate("createdAt").toLocalDate(),
                rst.getString("createdBy"),
                rst.getString("status"),
                rst.getDate("expectedDate").toLocalDate(),
                rst.getDouble("totalCost")
            ));
        }
        return allPurchaceOrders;
    }
    
}
