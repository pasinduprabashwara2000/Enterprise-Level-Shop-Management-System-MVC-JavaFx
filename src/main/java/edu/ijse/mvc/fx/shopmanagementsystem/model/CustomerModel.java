package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CustomerModel {

    public String saveCustomer(CustomerDTO customerDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Customer (name,phone,email,loyaltyCode) VALUES (?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerDTO.getName());
        st.setInt(2,customerDTO.getPhone());
        st.setString(3,customerDTO.getEmail());
        st.setString(4,customerDTO.getLoyaltyCode());

        return st.executeUpdate() > 0 ? "Customer Saved Successfully" : "Customer Saved Failed";

    }

    public String updateCustomer(CustomerDTO customerDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Customer SET name=?, phone=?, email=?, loyaltyCode=? WHERE customerId=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerDTO.getName());
        st.setInt(2,customerDTO.getPhone());
        st.setString(3,customerDTO.getEmail());
        st.setString(4,customerDTO.getLoyaltyCode());
        st.setString(5,customerDTO.getCustomerId());

        return st.executeUpdate() > 0 ? "Customer Updated Successfully" : "Customer Update Failed";

    }

    public String deleteCustomer(String customerID) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Customer WHERE customerId=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerID);

        return st.executeUpdate() > 0 ? "Customer Deleted Successfully" : "Customer Delete Failed";

    }

    public CustomerDTO searchCustomer(String customerID) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer WHERE customerId=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerID);

        var rst = st.executeQuery();
        if(rst.next()){
            return new CustomerDTO(
                    rst.getString("customerId"),
                    rst.getString("name"),
                    rst.getInt("phone"),
                    rst.getString("email"),
                    rst.getString("loyaltyCode")
            );
        }
        return null;

    }

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer";
        PreparedStatement st = conn.prepareStatement(sql);

        var rst = st.executeQuery();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while(rst.next()){
            allCustomers.add(new CustomerDTO(
                    rst.getString("customerId"),
                    rst.getString("name"),
                    rst.getInt("phone"),
                    rst.getString("email"),
                    rst.getString("loyaltyCode")
            ));
        }
        return allCustomers;
    }
}
