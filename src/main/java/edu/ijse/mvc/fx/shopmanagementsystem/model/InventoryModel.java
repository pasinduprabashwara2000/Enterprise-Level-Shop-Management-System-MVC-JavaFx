package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.InventoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class InventoryModel {

    public String saveInventory(InventoryDTO inventoryDTO) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Inventory VALUES (?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, inventoryDTO.getProductID());
        pstm.setInt(2, inventoryDTO.getQYT());
        pstm.setInt(3, inventoryDTO.getReOrderLevel());
        pstm.setInt(4, inventoryDTO.getReOrderQYT());
        pstm.setDate(5, Date.valueOf(inventoryDTO.getLastStockUpdate()));

        return pstm.executeUpdate() > 0 ? "Inventory Saved Successfully" : "Inventory Save Failed";
    
    }

    public String updateInventory(InventoryDTO inventoryDTO) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Inventory SET QYT=?, reOrderLevel=?, reOrderQYT=?, lastStockUpdate=? WHERE productID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, inventoryDTO.getQYT());
        pstm.setInt(2, inventoryDTO.getReOrderLevel());
        pstm.setInt(3, inventoryDTO.getReOrderQYT());
        pstm.setDate(4, Date.valueOf(inventoryDTO.getLastStockUpdate()));
        pstm.setString(5, inventoryDTO.getProductID());

        return pstm.executeUpdate() > 0 ? "Inventory Updated Successfully" : "Inventory Update Failed";

    }

    public String deleteInventory(String productID) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Inventory WHERE productID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, productID);

        return pstm.executeUpdate() > 0 ? "Inventory Deleted Successfully" : "Inventory Delete Failed";

    }

    public InventoryDTO searchInventory(String productID) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Inventory WHERE productID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, productID);
        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new InventoryDTO(
                    rst.getString("productID"),
                    rst.getInt("QYT"),
                    rst.getInt("reOrderLevel"),
                    rst.getInt("reOrderQYT"),
                    rst.getDate("lastStockUpdate").toLocalDate()
            );
        }
        return null;

    }

    public ArrayList<InventoryDTO> getAllInventories() throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Inventory";
        PreparedStatement pstm = conn.prepareStatement(sql);
        var rst = pstm.executeQuery();
        ArrayList<InventoryDTO> allInventories = new ArrayList<>();
        while (rst.next()) {
            allInventories.add(new InventoryDTO(
                    rst.getString("productID"),
                    rst.getInt("QYT"),
                    rst.getInt("reOrderLevel"),
                    rst.getInt("reOrderQYT"),
                    rst.getDate("lastStockUpdate").toLocalDate()
            ));
        }
        return allInventories;

    }

}
