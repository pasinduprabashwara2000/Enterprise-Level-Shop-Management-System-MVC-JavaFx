package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.PurchaseOrderDTO;

public class PurchaseOrderModel {

    public String savePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO purchase_order (supplier_id, total_cost, status, create_at, expected_date) VALUES (?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, purchaseOrderDTO.getSupplierId());
        pstm.setDouble(2, purchaseOrderDTO.getTotalCost());
        pstm.setString(3, purchaseOrderDTO.getStatus());
        pstm.setDate(4, java.sql.Date.valueOf(purchaseOrderDTO.getCreatedAt()));
        pstm.setDate(5, java.sql.Date.valueOf(purchaseOrderDTO.getExpectedDate()));
        
        return pstm.executeUpdate() > 0 ? "Purchase Order Saved Successfully" : "Failed to Save Purchase Order";
    }

    public String updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE purchase_order SET supplier_id = ?, total_cost = ?, status = ?, create_at = ?, expected_date = ? WHERE po_id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, purchaseOrderDTO.getSupplierId());
        pstm.setDouble(2, purchaseOrderDTO.getTotalCost());
        pstm.setString(3, purchaseOrderDTO.getStatus());
        pstm.setDate(4, java.sql.Date.valueOf(purchaseOrderDTO.getCreatedAt()));
        pstm.setDate(5, java.sql.Date.valueOf(purchaseOrderDTO.getExpectedDate()));
        pstm.setString(6, purchaseOrderDTO.getPoId());
        
        return pstm.executeUpdate() > 0 ? "Purchase Order Updated Successfully" : "Failed to Update Purchase Order";
    }

    public String deletePurchaseOrder(String poId) throws Exception{
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM purchase_order WHERE po_id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, poId);
        
        return pstm.executeUpdate() > 0 ? "Purchase Order Deleted Successfully" : "Failed to Delete Purchase Order";
    }

    public PurchaseOrderDTO searchPurchaseOrder(String poId) throws Exception{
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM purchase_order WHERE po_id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, poId);
        
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new PurchaseOrderDTO(
                rs.getString("po_id"),
                rs.getString("supplier_id"),
                rs.getDouble("total_cost"),
                rs.getString("status"),
                rs.getDate("create_at").toLocalDate(),
                rs.getDate("expected_date").toLocalDate()
            );
        }
        return null;
    }

    public ArrayList<PurchaseOrderDTO> getAllPurchaseOrders() throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM purchase_order";
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        ResultSet rs = pstm.executeQuery();
        ArrayList<PurchaseOrderDTO> purchaseOrders = new ArrayList<>();
        while (rs.next()) {
            purchaseOrders.add(new PurchaseOrderDTO(
                rs.getString("po_id"),
                rs.getString("supplier_id"),
                rs.getDouble("total_cost"),
                rs.getString("status"),
                rs.getDate("create_at").toLocalDate(),
                rs.getDate("expected_date").toLocalDate()
            ));
        }
        return purchaseOrders;
    }

}
