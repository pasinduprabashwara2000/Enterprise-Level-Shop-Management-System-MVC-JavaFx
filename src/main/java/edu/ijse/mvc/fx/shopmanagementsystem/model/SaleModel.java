package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SaleModel {

    public String saveSale(SaleDTO dto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Sale (customer_id, sale_date, total_amount, discount, net_total) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, dto.getCustomerID());
        ps.setDate(2, Date.valueOf(dto.getSaleDate()));
        ps.setDouble(3, dto.getTotalAmount());
        ps.setDouble(4, dto.getDiscount());
        ps.setDouble(5, dto.getNetTotal());

        return ps.executeUpdate() > 0
                ? "Sale saved successfully!"
                : "Failed to save sale!";
    }

    public String updateSale(SaleDTO dto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Sale SET customer_id = ?, sale_date = ?, total_amount = ?, discount = ?, net_total = ? WHERE sale_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, dto.getCustomerID());
        ps.setDate(2, Date.valueOf(dto.getSaleDate()));
        ps.setDouble(3, dto.getTotalAmount());
        ps.setDouble(4, dto.getDiscount());
        ps.setDouble(5, dto.getNetTotal());
        ps.setString(6, dto.getSaleID());

        return ps.executeUpdate() > 0
                ? "Sale updated successfully!"
                : "Sale not found!";
    }

    public String deleteSale(String saleID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Sale WHERE sale_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, saleID);

        return ps.executeUpdate() > 0
                ? "Sale deleted successfully!"
                : "Sale not found!";
    }

    public SaleDTO searchSale(String saleID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Sale WHERE sale_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, saleID);

        ResultSet rs = ps.executeQuery();

        return rs.next()
                ? new SaleDTO(
                rs.getString("sale_id"),
                rs.getString("customer_id"),
                rs.getDate("sale_date").toLocalDate(),
                rs.getDouble("total_amount"),
                rs.getDouble("discount"),
                rs.getDouble("net_total"))
                : null;
    }

    public ArrayList<SaleDTO> getAllSales() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Sale";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<SaleDTO> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new SaleDTO(
                    rs.getString("sale_id"),
                    rs.getString("customer_id"),
                    rs.getDate("sale_date").toLocalDate(),
                    rs.getDouble("total_amount"),
                    rs.getDouble("discount"),
                    rs.getDouble("net_total")
            ));
        }

        return list;
    }
}
