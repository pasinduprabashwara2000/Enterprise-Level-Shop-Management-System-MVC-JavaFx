package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SupplierDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class SupplierModel {

    public String saveSupplier(SupplierDTO supplierDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Supplier (name, contactPerson, phone, email, address) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierDTO.getName());
        pstm.setString(2, supplierDTO.getContactPerson());
        pstm.setInt(3, supplierDTO.getPhone());
        pstm.setString(4, supplierDTO.getEmail());
        pstm.setString(5, supplierDTO.getAddress());
        
        return pstm.executeUpdate() > 0 ? "Supplier Saved Successfully" : "Supplier Save Failed";    
    
    }

    public String updateSupplier(SupplierDTO supplierDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Supplier SET name=?, contactPerson=?, phone=?, email=?, address=? WHERE supplierID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierDTO.getName());
        pstm.setString(2, supplierDTO.getContactPerson());
        pstm.setInt(3, supplierDTO.getPhone());
        pstm.setString(4, supplierDTO.getEmail());
        pstm.setString(5, supplierDTO.getAddress());
        pstm.setString(6, supplierDTO.getSupplierID());

        return pstm.executeUpdate() > 0 ? "Supplier Updated Successfully" : "Supplier Update Failed";    
    
    }

    public String deleteSupplier(String supplierID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Supplier WHERE supplierID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierID);

        return pstm.executeUpdate() > 0 ? "Supplier Deleted Successfully" : "Supplier Delete Failed";    
    
    }

    public SupplierDTO searchSupplier(String supplierID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supplier WHERE supplierID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, supplierID);

        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new SupplierDTO(
                    rst.getString("supplierID"),
                    rst.getString("name"),
                    rst.getString("contactPerson"),
                    rst.getInt("phone"),
                    rst.getString("email"),
                    rst.getString("address")
            );
        }
        return null;
    
    }

    public ArrayList<SupplierDTO> getAllSuppliers() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        var rst = pstm.executeQuery();
        ArrayList<SupplierDTO> supplierList = new ArrayList<>();
        while (rst.next()) {
            supplierList.add(new SupplierDTO(
                    rst.getString("supplierID"),
                    rst.getString("name"),
                    rst.getString("contactPerson"),
                    rst.getInt("phone"),
                    rst.getString("email"),
                    rst.getString("address")
            ));
        }
        return supplierList;
    }
    
}
