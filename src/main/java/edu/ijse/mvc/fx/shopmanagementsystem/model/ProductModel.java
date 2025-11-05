package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class ProductModel {

    public String saveProduct(ProductDTO productDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Product (productID, SKU, barCode, name, unit, unitPrice, taxRate, active, categoryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productDTO.getProductID());
        pstm.setString(2, productDTO.getSKU());
        pstm.setInt(3, productDTO.getBarCode());
        pstm.setString(4, productDTO.getName());
        pstm.setString(5, productDTO.getUnit());
        pstm.setDouble(6, productDTO.getUnitPrice());
        pstm.setDouble(7, productDTO.getTaxRate());
        pstm.setBoolean(8, productDTO.isActive());
        pstm.setString(9, productDTO.getCategoryID());

        return pstm.executeUpdate() > 0 ? "Product Saved Successfully" : "Product Save Failed";
    }

    public String updateProduct(ProductDTO productDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Product SET SKU = ?, barCode = ?, name = ?, unit = ?, unitPrice = ?, taxRate = ?, active = ?, categoryID = ? WHERE productID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productDTO.getSKU());
        pstm.setInt(2, productDTO.getBarCode());
        pstm.setString(3, productDTO.getName());
        pstm.setString(4, productDTO.getUnit());
        pstm.setDouble(5, productDTO.getUnitPrice());
        pstm.setDouble(6, productDTO.getTaxRate());
        pstm.setBoolean(7, productDTO.isActive());
        pstm.setString(8, productDTO.getCategoryID());
        pstm.setString(9, productDTO.getProductID());

        return pstm.executeUpdate() > 0 ? "Product Updated Successfully" : "Product Update Failed";
    }

    public String deleteProduct(String productID) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Product WHERE productID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productID);

        return pstm.executeUpdate() > 0 ? "Product Deleted Successfully" : "Product Deletion Failed";
    }

    public ProductDTO searchProduct(String productID) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Product WHERE productID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productID);
        var resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return new ProductDTO(
                    resultSet.getString("productID"),
                    resultSet.getString("SKU"),
                    resultSet.getInt("barCode"),
                    resultSet.getString("name"),
                    resultSet.getString("unit"),
                    resultSet.getDouble("unitPrice"),
                    resultSet.getDouble("taxRate"),
                    resultSet.getBoolean("active"),
                    resultSet.getString("categoryID")
            );
        }
        return null;
    }

    public ArrayList<ProductDTO> getAllProducts() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Product";
        PreparedStatement pstm = connection.prepareStatement(sql);
        var resultSet = pstm.executeQuery();
        ArrayList<ProductDTO> productList = new ArrayList<>();

        while (resultSet.next()) {
            productList.add(new ProductDTO(
                    resultSet.getString("productID"),
                    resultSet.getString("SKU"),
                    resultSet.getInt("barCode"),
                    resultSet.getString("name"),
                    resultSet.getString("unit"),
                    resultSet.getDouble("unitPrice"),
                    resultSet.getDouble("taxRate"),
                    resultSet.getBoolean("active"),
                    resultSet.getString("categoryID")
            ));
        }
        return productList;
    }
}
