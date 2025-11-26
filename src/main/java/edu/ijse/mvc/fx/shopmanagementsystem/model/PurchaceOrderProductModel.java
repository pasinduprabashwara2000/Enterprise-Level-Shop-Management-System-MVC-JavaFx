package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PurchaceOrderProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class PurchaceOrderProductModel {

    public String savePurchaceOrderProduct(PurchaceOrderProductDTO purchaceOrderProductDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO PurchaseOrderProduct (poItemId, poId, quantityOrdered, quantityReceived, unitCost, lineTotal) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, purchaceOrderProductDTO.getPoItemId());
        pstm.setString(2, purchaceOrderProductDTO.getPoId());
        pstm.setInt(3, purchaceOrderProductDTO.getQuantityOrdered());
        pstm.setInt(4, purchaceOrderProductDTO.getQuantityReceived());
        pstm.setDouble(5, purchaceOrderProductDTO.getUnitCost());
        pstm.setDouble(6, purchaceOrderProductDTO.getLineTotal());

        return pstm.executeUpdate() > 0 ? "Purchase Order Product Saved Successfully" : "Purchase Order Product Save Failed";

    }

    public String updatePurchaceOrderProduct(PurchaceOrderProductDTO purchaceOrderProductDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE PurchaseOrderProduct SET poId=?, quantityOrdered=?, quantityReceived=?, unitCost=?, lineTotal=? WHERE poItemId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, purchaceOrderProductDTO.getPoId());
        pstm.setInt(2, purchaceOrderProductDTO.getQuantityOrdered());
        pstm.setInt(3, purchaceOrderProductDTO.getQuantityReceived());
        pstm.setDouble(4, purchaceOrderProductDTO.getUnitCost());
        pstm.setDouble(5, purchaceOrderProductDTO.getLineTotal());
        pstm.setString(6, purchaceOrderProductDTO.getPoItemId());

        return pstm.executeUpdate() > 0 ? "Purchase Order Product Updated Successfully" : "Purchase Order Product Update Failed";

    }

    public String deletePurchaceOrderProduct(String poItemId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM PurchaseOrderProduct WHERE poItemId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, poItemId);

        return pstm.executeUpdate() > 0 ? "Purchase Order Product Deleted Successfully" : "Purchase Order Product Delete Failed";

    }

    public PurchaceOrderProductDTO searchPurchaceOrderProduct(String poItemId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PurchaseOrderProduct WHERE poItemId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, poItemId);
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return new PurchaceOrderProductDTO(
                rst.getString("poItemId"),
                rst.getString("poId"),
                rst.getInt("quantityOrdered"),
                rst.getInt("quantityReceived"),
                rst.getDouble("unitCost"),
                rst.getDouble("lineTotal")
            );
        }
        return null;
        
    }

    public ArrayList<PurchaceOrderProductDTO> getAllPurchaceOrderProducts() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM PurchaseOrderProduct";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        ArrayList<PurchaceOrderProductDTO> purchaceOrderProductList = new ArrayList<>();
        while (rst.next()) {
            purchaceOrderProductList.add(new PurchaceOrderProductDTO(
                rst.getString("poItemId"),
                rst.getString("poId"),
                rst.getInt("quantityOrdered"),
                rst.getInt("quantityReceived"),
                rst.getDouble("unitCost"),
                rst.getDouble("lineTotal")
            ));
        }
        return purchaceOrderProductList;
    }
    
}
