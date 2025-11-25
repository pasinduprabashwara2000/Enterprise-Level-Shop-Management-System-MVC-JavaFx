package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class SaleModel {
    
    public String saveSale(SaleDTO saleDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Sale VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleDTO.getSaleID());
        pstm.setString(2, saleDTO.getUserID());
        pstm.setString(3, saleDTO.getCustomerID());
        pstm.setDouble(4, saleDTO.getSubTotal());
        pstm.setDouble(5, saleDTO.getTaxTotal());
        pstm.setDouble(6, saleDTO.getDiscountTotal());
        pstm.setDouble(7, saleDTO.getGrandTotal());
        pstm.setDate(8, Date.valueOf(saleDTO.getDate()));
        pstm.setObject(9, saleDTO.getStatus());

        return pstm.executeUpdate() > 0 ? "Sale Saved Successfully" : "Sale Save Failed";
    
    }

    public String updateSale(SaleDTO saleDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Sale SET userID=?, customerID=?, subTotal=?, taxTotal=?, discountTotal=?, grandTotal=?, date=?, status=? WHERE saleID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleDTO.getUserID());
        pstm.setString(2, saleDTO.getCustomerID());
        pstm.setDouble(3, saleDTO.getSubTotal());
        pstm.setDouble(4, saleDTO.getTaxTotal());
        pstm.setDouble(5, saleDTO.getDiscountTotal());
        pstm.setDouble(6, saleDTO.getGrandTotal());
        pstm.setDate(7, Date.valueOf(saleDTO.getDate()));
        pstm.setObject(8, saleDTO.getStatus());
        pstm.setString(9, saleDTO.getSaleID());

        return pstm.executeUpdate() > 0 ? "Sale Updated Successfully" : "Sale Update Failed";
    
    }

    public String deleteSale(String saleID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Sale WHERE saleID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleID);

        return pstm.executeUpdate() > 0 ? "Sale Deleted Successfully" : "Sale Delete Failed";
    
    }

    public SaleDTO searchSale(String saleID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Sale WHERE saleID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, saleID);

        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new SaleDTO(
                    rst.getString("saleID"),
                    rst.getString("userID"),
                    rst.getString("customerID"),
                    rst.getDouble("subTotal"),
                    rst.getDouble("taxTotal"),
                    rst.getDouble("discountTotal"),
                    rst.getDouble("grandTotal"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("status")
            );
        }
        return null;
    
    }

    public ArrayList<SaleDTO> getAllSales() throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Sale";
        PreparedStatement pstm = connection.prepareStatement(sql);

        var rst = pstm.executeQuery();
        ArrayList<SaleDTO> saleList = new ArrayList<>();
        while (rst.next()) {
            saleList.add(new SaleDTO(
                    rst.getString("saleID"),
                    rst.getString("userID"),
                    rst.getString("customerID"),
                    rst.getDouble("subTotal"),
                    rst.getDouble("taxTotal"),
                    rst.getDouble("discountTotal"),
                    rst.getDouble("grandTotal"),
                    rst.getDate("date").toLocalDate(),
                    rst.getString("status")
            ));
        }
        return saleList;
    
    }

}
