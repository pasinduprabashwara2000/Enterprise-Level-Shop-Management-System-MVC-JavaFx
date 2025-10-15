package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ReturnProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class ReturnProductModel {

    public String saveReturnProduct(ReturnProductDTO returnProductDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO ReturnProduct VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnProductDTO.getReturnItemId());
        pstm.setString(2, returnProductDTO.getReturnId());
        pstm.setString(3, returnProductDTO.getProductId());
        pstm.setString(4, returnProductDTO.getSaleItemId());
        pstm.setInt(5, returnProductDTO.getQuantity());
        pstm.setDouble(6, returnProductDTO.getRefundAmount());
        pstm.setObject(7, returnProductDTO.getAction());

        return pstm.executeUpdate() > 0 ? "Return Product Saved Successfully" : "Return Product Save Failed";

    }

    public String updateReturnProduct (ReturnProductDTO returnProductDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE ReturnProduct SET returnId=?, productId=?, saleItemId=?, quantity=?, refundAmount=?, action=? WHERE returnItemId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnProductDTO.getReturnId());
        pstm.setString(2, returnProductDTO.getProductId());
        pstm.setString(3, returnProductDTO.getSaleItemId());
        pstm.setInt(4, returnProductDTO.getQuantity());
        pstm.setDouble(5, returnProductDTO.getRefundAmount());
        pstm.setObject(6, returnProductDTO.getAction());
        pstm.setString(7, returnProductDTO.getReturnItemId());

        return pstm.executeUpdate() > 0 ? "Return Product Updated Successfully" : "Return Product Update Failed";

    }

    public String deleteReturnProduct(String returnItemId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM ReturnProduct WHERE returnItemId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnItemId);

        return pstm.executeUpdate() > 0 ? "Return Product Deleted Successfully" : "Return Product Delete Failed";

    }

    public ReturnProductDTO searchReturnProduct(String returnItemId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM ReturnProduct WHERE returnItemId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnItemId);
        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new ReturnProductDTO(
                rst.getString("returnItemId"),
                rst.getString("returnId"),
                rst.getString("productId"),
                rst.getString("saleItemId"),
                rst.getInt("quantity"),
                rst.getDouble("refundAmount"),
                (ReturnProductDTO.Action) rst.getObject("action")
            );
        }
        return null;

    }

    public ArrayList<ReturnProductDTO> getAllReturnProducts() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM ReturnProduct";
        PreparedStatement pstm = connection.prepareStatement(sql);
        var rst = pstm.executeQuery();
        ArrayList<ReturnProductDTO> allReturnProducts = new ArrayList<>();
        while (rst.next()) {
            allReturnProducts.add(new ReturnProductDTO(
                rst.getString("returnItemId"),
                rst.getString("returnId"),
                rst.getString("productId"),
                rst.getString("saleItemId"),
                rst.getInt("quantity"),
                rst.getDouble("refundAmount"),
                (ReturnProductDTO.Action) rst.getObject("action")
            ));
        }
        return allReturnProducts;

    }

    public ArrayList<ReturnProductDTO> getReturnProductsByReturnId(String returnId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM ReturnProduct WHERE returnId=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, returnId);
        var rst = pstm.executeQuery();
        ArrayList<ReturnProductDTO> returnProductsByReturnId = new ArrayList<>();
        while (rst.next()) {
            returnProductsByReturnId.add(new ReturnProductDTO(
                rst.getString("returnItemId"),
                rst.getString("returnId"),
                rst.getString("productId"),
                rst.getString("saleItemId"),
                rst.getInt("quantity"),
                rst.getDouble("refundAmount"),
                (ReturnProductDTO.Action) rst.getObject("action")
            ));
        }
        return returnProductsByReturnId;

    }
    
}
