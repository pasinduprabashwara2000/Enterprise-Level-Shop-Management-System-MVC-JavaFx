package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PaymentModel {

    public String savePayment(PaymentDTO dto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Payment (customer_id, method, amount, reference, received_at) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, dto.getCustomerID());
        ps.setString(2, dto.getMethod());
        ps.setDouble(3, dto.getAmount());
        ps.setString(4, dto.getReference());
        ps.setDate(5, Date.valueOf(dto.getReceivedAt()));

        return ps.executeUpdate() > 0
                ? "Payment saved successfully!"
                : "Failed to save payment!";
    }

    public String updatePayment(PaymentDTO dto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Payment SET customer_id = ?, method = ?, amount = ?, reference = ?, received_at = ? WHERE payment_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, dto.getCustomerID());
        ps.setString(2, dto.getMethod());
        ps.setDouble(3, dto.getAmount());
        ps.setString(4, dto.getReference());
        ps.setDate(5, Date.valueOf(dto.getReceivedAt()));
        ps.setString(6, dto.getPaymentID());

        return ps.executeUpdate() > 0
                ? "Payment updated successfully!"
                : "Payment not found!";
    }

    public String deletePayment(String paymentID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Payment WHERE payment_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, paymentID);

        return ps.executeUpdate() > 0
                ? "Payment deleted successfully!"
                : "Payment not found!";
    }

    public PaymentDTO searchPayment(String paymentID) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Payment WHERE payment_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, paymentID);

        ResultSet rs = ps.executeQuery();

        return rs.next()
                ? new PaymentDTO(
                rs.getString("payment_id"),
                rs.getString("customer_id"),
                rs.getString("method"),
                rs.getDouble("amount"),
                rs.getString("reference"),
                rs.getDate("received_at").toLocalDate())
                : null;
    }

    public ArrayList<PaymentDTO> getAllPayments() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Payment";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ArrayList<PaymentDTO> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new PaymentDTO(
                    rs.getString("payment_id"),
                    rs.getString("customer_id"),
                    rs.getString("method"),
                    rs.getDouble("amount"),
                    rs.getString("reference"),
                    rs.getDate("received_at").toLocalDate()
            ));
        }

        return list;
    }
}
