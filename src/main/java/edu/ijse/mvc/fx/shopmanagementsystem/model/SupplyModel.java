package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplyDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class SupplyModel {

    public String saveSupply(SupplyDTO supplyDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Supply VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplyDTO.getProductID());
        pstm.setString(2, supplyDTO.getSupplierID());
        pstm.setDouble(3, supplyDTO.getLastCost());
        pstm.setString(4, supplyDTO.getSupplierProductCode());

        return pstm.executeUpdate() > 0 ? "Supply Saved Successfully" : "Supply Save Failed";

    }

    public String updateSupply(SupplyDTO supplyDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Supply SET lastCost=?, supplierProductCode=? WHERE productID=? AND supplierID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDouble(1, supplyDTO.getLastCost());
        pstm.setString(2, supplyDTO.getSupplierProductCode());
        pstm.setString(3, supplyDTO.getProductID());
        pstm.setString(4, supplyDTO.getSupplierID());

        return pstm.executeUpdate() > 0 ? "Supply Updated Successfully" : "Supply Update Failed";

    }

    public String deleteSupply(String productID, String supplierID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Supply WHERE productID=? AND supplierID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productID);
        pstm.setString(2, supplierID);

        return pstm.executeUpdate() > 0 ? "Supply Deleted Successfully" : "Supply Delete Failed";
    }

    public SupplyDTO searchSupply(String productID, String supplierID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supply WHERE productID=? AND supplierID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        
        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new SupplyDTO(
                    rst.getString("productID"),
                    rst.getString("supplierID"),
                    rst.getDouble("lastCost"),
                    rst.getString("supplierProductCode")
            );
        }
        
        return null;

    }

    public ArrayList<SupplyDTO> getAllSupplies() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supply";
        PreparedStatement pstm = connection.prepareStatement(sql);
        var rst = pstm.executeQuery();
        
        ArrayList<SupplyDTO> supplyList = new ArrayList<>();
        while (rst.next()) {
            supplyList.add(new SupplyDTO(
                    rst.getString("productID"),
                    rst.getString("supplierID"),
                    rst.getDouble("lastCost"),
                    rst.getString("supplierProductCode")
            ));
        }
        return supplyList;
    }

}
