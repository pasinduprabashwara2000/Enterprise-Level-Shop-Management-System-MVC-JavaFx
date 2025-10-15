package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.UserDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class UserModel {

    public String saveUser(UserDTO userDTO) throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO User VALUES (?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userDTO.getUserID());
        pstm.setString(2, userDTO.getUserName());
        pstm.setString(3, userDTO.getPassword());
        pstm.setString(4, userDTO.getRoleID());
        pstm.setBoolean(5, userDTO.isActive());
        pstm.setDate(6, new java.sql.Date(userDTO.getCreatedAt().getTime()));
        
        return pstm.executeUpdate() > 0 ? "User Saved Successfully" : "User Save Failed";
        
    }

    public String updateUser(UserDTO userDTO) throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE User SET userName=?, password=?, roleID=?, active=?, createdAt=? WHERE userID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userDTO.getUserName());
        pstm.setString(2, userDTO.getPassword());
        pstm.setString(3, userDTO.getRoleID());
        pstm.setBoolean(4, userDTO.isActive());
        pstm.setDate(5, new java.sql.Date(userDTO.getCreatedAt().getTime()));
        pstm.setString(6, userDTO.getUserID());
        
        return pstm.executeUpdate() > 0 ? "User Updated Successfully" : "User Update Failed";
        
    }

    public String deleteUser(String userID) throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM User WHERE userID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userID);
        
        return pstm.executeUpdate() > 0 ? "User Deleted Successfully" : "User Delete Failed";
        
    }

    public UserDTO searchUser(String userID) throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM User WHERE userID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userID);
        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new UserDTO(
                    rst.getString("userID"),
                    rst.getString("userName"),
                    rst.getString("password"),
                    rst.getString("roleID"),
                    rst.getBoolean("active"),
                    rst.getDate("createdAt")
            );
        }
        return null;
        
    }

    public ArrayList<UserDTO> getAllUsers() throws Exception {
        
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM User";
        PreparedStatement pstm = conn.prepareStatement(sql);
        var rst = pstm.executeQuery();
        ArrayList<UserDTO> userList = new ArrayList<>();
        while (rst.next()) {
            userList.add(new UserDTO(
                    rst.getString("userID"),
                    rst.getString("userName"),
                    rst.getString("password"),
                    rst.getString("roleID"),
                    rst.getBoolean("active"),
                    rst.getDate("createdAt")
            ));
        }
        return userList;
        
    }
    
}
