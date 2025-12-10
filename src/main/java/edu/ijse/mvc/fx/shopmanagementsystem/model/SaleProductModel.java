package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class SaleProductModel {

    public String saveSaleProduct(SaleProductDTO saleProductDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO SaleProduct VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleProductDTO.getSaleProductID());
        pstm.setString(2, saleProductDTO.getSaleID());
        pstm.setString(3, saleProductDTO.getProductID());
        pstm.setString(4, saleProductDTO.getPromotionID());
        pstm.setInt(5, saleProductDTO.getQuantity());
        pstm.setDouble(6, saleProductDTO.getUnitPrice());
        pstm.setDouble(7, saleProductDTO.getLineDiscount());
        pstm.setDouble(8, saleProductDTO.getLineTax());
        pstm.setDouble(9, saleProductDTO.getLineTotal());

        return pstm.executeUpdate() > 0 ? "Sale Product Saved Successfully" : "Sale Product Save Failed";
    }

    public String updateSaleProduct(SaleProductDTO saleProductDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE SaleProduct SET saleID=?, productID=?, promotionID=?, quantity=?, unitPrice=?, lineDiscount=?, lineTax=?, lineTotal=? WHERE saleProductID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleProductDTO.getSaleID());
        pstm.setString(2, saleProductDTO.getProductID());
        pstm.setString(3, saleProductDTO.getPromotionID());
        pstm.setInt(4, saleProductDTO.getQuantity());
        pstm.setDouble(5, saleProductDTO.getUnitPrice());
        pstm.setDouble(6, saleProductDTO.getLineDiscount());
        pstm.setDouble(7, saleProductDTO.getLineTax());
        pstm.setDouble(8, saleProductDTO.getLineTotal());
        pstm.setString(9, saleProductDTO.getSaleProductID());

        return pstm.executeUpdate() > 0 ? "Sale Product Updated Successfully" : "Sale Product Update Failed";
    }

    public String deleteSaleProduct(String saleProductID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM SaleProduct WHERE saleProductID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleProductID);

        return pstm.executeUpdate() > 0 ? "Sale Product Deleted Successfully" : "Sale Product Delete Failed";
    }

    public SaleProductDTO searchSaleProduct(String saleProductID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM SaleProduct WHERE saleProductID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleProductID);

        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return new SaleProductDTO(
                    rst.getString("saleProductID"),
                    rst.getString("saleID"),
                    rst.getString("productID"),
                    rst.getString("promotionID"),
                    rst.getInt("quantity"),
                    rst.getDouble("unitPrice"),
                    rst.getDouble("lineDiscount"),
                    rst.getDouble("lineTax"),
                    rst.getDouble("lineTotal")
            );
        }
        return null;
    }

    public ArrayList<SaleProductDTO> getAllSaleProducts() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM SaleProduct";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        ArrayList<SaleProductDTO> saleProductList = new ArrayList<>();
        while (rst.next()) {
            saleProductList.add(new SaleProductDTO(
                    rst.getString("saleProductID"),
                    rst.getString("saleID"),
                    rst.getString("productID"),
                    rst.getString("promotionID"),
                    rst.getInt("quantity"),
                    rst.getDouble("unitPrice"),
                    rst.getDouble("lineDiscount"),
                    rst.getDouble("lineTax"),
                    rst.getDouble("lineTotal")
            ));
        }
        return saleProductList;
    }

}