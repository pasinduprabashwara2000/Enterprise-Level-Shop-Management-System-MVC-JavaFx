package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class PaymentModel {

    public String savePayment(PaymentDTO paymentDTO) throws Exception {

    Connection connection = DBConnection.getInstance().getConnection();
    String sql = "INSERT INTO Payment VALUES (?,?,?,?,?,?)";
    PreparedStatement pstm = connection.prepareStatement(sql);
    pstm.setString(1, paymentDTO.getPaymentID());
    pstm.setString(2, paymentDTO.getSaleID());
    pstm.setObject(3, paymentDTO.getMethod());
    pstm.setDouble(4, paymentDTO.getAmount());
    pstm.setString(5, paymentDTO.getReference());
    pstm.setDate(6, paymentDTO.getReceivedAt());

    return pstm.executeUpdate() > 0 ? "Payment Saved Successfully" : "Payment Save Failed"; 
        
    }

    public String updatePayment(PaymentDTO paymentDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Payment SET saleID=?, method=?, amount=?, reference=?, receivedAt=? WHERE paymentID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, paymentDTO.getSaleID());
        pstm.setObject(2, paymentDTO.getMethod());
        pstm.setDouble(3, paymentDTO.getAmount());
        pstm.setString(4, paymentDTO.getReference());
        pstm.setDate(5, paymentDTO.getReceivedAt());
        pstm.setString(6, paymentDTO.getPaymentID());

        return pstm.executeUpdate() > 0 ? "Payment Updated Successfully" : "Payment Update Failed"; 
        
    }

    public String deletePayment(String paymentID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Payment WHERE paymentID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, paymentID);

        return pstm.executeUpdate() > 0 ? "Payment Deleted Successfully" : "Payment Delete Failed"; 
        
    }

    public PaymentDTO searchPayment(String paymentID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Payment WHERE paymentID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, paymentID);

        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new PaymentDTO(
                rst.getString("paymentID"),
                rst.getString("saleID"),
                PaymentDTO.Method.valueOf(rst.getString("method")),
                rst.getDouble("amount"),
                rst.getString("reference"),
                rst.getDate("receivedAt")
            );
        }
        return null;
        
    }

    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Payment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        ArrayList<PaymentDTO> payments = new ArrayList<>();
        while (rst.next()) {
            payments.add(new PaymentDTO(
                rst.getString("paymentID"),
                rst.getString("saleID"),
                PaymentDTO.Method.valueOf(rst.getString("method")),
                rst.getDouble("amount"),
                rst.getString("reference"),
                rst.getDate("receivedAt")
            ));
        }
        return payments;
    }
    
}
