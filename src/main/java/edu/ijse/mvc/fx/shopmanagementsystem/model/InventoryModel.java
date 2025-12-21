package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.InventoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InventoryModel {

    public String saveInventory(InventoryDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Inventory (productID, QYT, reOrderLevel, reOrderQYT, lastStockUpdate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, dto.getProductID());
        pst.setInt(2, dto.getQYT());
        pst.setInt(3, dto.getReOrderLevel());
        pst.setInt(4, dto.getReOrderQYT());
        pst.setDate(5, java.sql.Date.valueOf(dto.getLastStockUpdate()));

        return pst.executeUpdate() > 0 ? "Inventory saved successfully" : "Failed to save inventory";
    }

    public String updateInventory(InventoryDTO dto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Inventory SET productID=?, QYT=?, reOrderLevel=?, reOrderQYT=?, lastStockUpdate=? WHERE inventoryID=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, dto.getProductID());
        pst.setInt(2, dto.getQYT());
        pst.setInt(3, dto.getReOrderLevel());
        pst.setInt(4, dto.getReOrderQYT());
        pst.setDate(5, java.sql.Date.valueOf(dto.getLastStockUpdate()));
        pst.setString(6, dto.getInventoryID());

        return pst.executeUpdate() > 0 ? "Inventory updated successfully" : "Failed to update inventory";
    }

    public String deleteInventory(String inventoryID) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Inventory WHERE inventoryID=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, inventoryID);
        return pst.executeUpdate() > 0 ? "Inventory deleted successfully" : "Failed to delete inventory";
    }

    public InventoryDTO searchInventory(String inventoryID) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Inventory WHERE inventoryID=?";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, inventoryID);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return new InventoryDTO(
                    rs.getString("inventoryID"),
                    rs.getString("productID"),
                    rs.getInt("QYT"),
                    rs.getInt("reOrderLevel"),
                    rs.getInt("reOrderQYT"),
                    rs.getDate("lastStockUpdate").toLocalDate()
            );
        }
        return null;
    }

    public ArrayList<InventoryDTO> getAllInventories() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Inventory";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        ArrayList<InventoryDTO> inventories = new ArrayList<>();
        while (rs.next()) {
            inventories.add(new InventoryDTO(
                    rs.getString("inventoryID"),
                    rs.getString("productID"),
                    rs.getInt("QYT"),
                    rs.getInt("reOrderLevel"),
                    rs.getInt("reOrderQYT"),
                    rs.getDate("lastStockUpdate").toLocalDate()
            ));
        }
        return inventories;
    }
}
