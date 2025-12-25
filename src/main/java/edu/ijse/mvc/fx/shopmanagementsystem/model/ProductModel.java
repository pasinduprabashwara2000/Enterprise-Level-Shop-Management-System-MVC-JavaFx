package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.util.CrudUtil;

public class ProductModel {

    public String saveProduct(ProductDTO productDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Product (SKU, barCode, name, unitPrice, qyt, active, categoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productDTO.getSKU());
        pstm.setInt(2, productDTO.getBarCode());
        pstm.setString(3, productDTO.getName());
        pstm.setDouble(4, productDTO.getUnitPrice());
        pstm.setInt(5, productDTO.getQyt());
        pstm.setBoolean(6, productDTO.isActive());
        pstm.setString(7, productDTO.getCategoryID());

        return pstm.executeUpdate() > 0 ? "Product Saved Successfully" : "Product Save Failed";
    }

    public String updateProduct(ProductDTO productDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Product SET SKU = ?, barCode = ?, name = ?, unitPrice = ?, qyt = ?, active = ?, categoryID = ? WHERE productID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, productDTO.getSKU());
        pstm.setInt(2, productDTO.getBarCode());
        pstm.setString(3, productDTO.getName());
        pstm.setDouble(4, productDTO.getUnitPrice());
        pstm.setInt(5, productDTO.getQyt());
        pstm.setBoolean(6, productDTO.isActive());
        pstm.setString(7, productDTO.getCategoryID());
        pstm.setString(8, productDTO.getProductID());

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
                    resultSet.getDouble("unitPrice"),
                    resultSet.getInt("qyt"),
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
                    resultSet.getDouble("unitPrice"),
                    resultSet.getInt("qyt"),
                    resultSet.getBoolean("active"),
                    resultSet.getString("categoryID")
            ));
        }
        return productList;
    }

    public boolean decreaseProductQYT(String id, int qyt) throws Exception {

        boolean isUpdated = CrudUtil.execute("UPDATE Product SET qyt -? WHERE productID = ?",
                id,
                qyt);

        return isUpdated;
    }
}
