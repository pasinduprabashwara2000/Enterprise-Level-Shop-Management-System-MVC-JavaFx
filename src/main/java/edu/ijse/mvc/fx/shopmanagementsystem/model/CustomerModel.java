package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CustomerModel {

    public String saveCustomer(CustomerDTO customerDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES (?,?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerDTO.getCustomerID());
        st.setString(2,customerDTO.getName());
        st.setInt(3,customerDTO.getPhone());
        st.setString(4,customerDTO.getEmail());
        st.setString(5,customerDTO.getLoyaltyCode());

        return st.executeUpdate() > 0 ? "Customer Saved Successfully" : "Customer Saved Failed";

    }
}
