package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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

    public String updateCustomer(CustomerDTO customerDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE customer SET name=?, phone=?, email=?, loyaltyCode=? WHERE customerID=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerDTO.getName());
        st.setInt(2,customerDTO.getPhone());
        st.setString(3,customerDTO.getEmail());
        st.setString(4,customerDTO.getLoyaltyCode());
        st.setString(5,customerDTO.getCustomerID());

        return st.executeUpdate() > 0 ? "Customer Updated Successfully" : "Customer Update Failed";

    }

    public String deleteCustomer(String customerID) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM customer WHERE customerID=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerID);

        return st.executeUpdate() > 0 ? "Customer Deleted Successfully" : "Customer Delete Failed";

    }

    public CustomerDTO searchCustomer(String customerID) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE customerID=?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1,customerID);

        var rst = st.executeQuery();
        if(rst.next()){
            return new CustomerDTO(
                    rst.getString("customerID"),
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
        String sql = "SELECT * FROM customer";
        PreparedStatement st = conn.prepareStatement(sql);

        var rst = st.executeQuery();
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        while(rst.next()){
            allCustomers.add(new CustomerDTO(
                    rst.getString("customerID"),
                    rst.getString("name"),
                    rst.getInt("phone"),
                    rst.getString("email"),
                    rst.getString("loyaltyCode")
            ));
        }
        return allCustomers;

    }
}
