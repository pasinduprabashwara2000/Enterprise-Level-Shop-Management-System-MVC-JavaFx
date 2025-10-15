package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.RoleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class RoleModel {

    public String saveRole(RoleDTO roleDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Role VALUES (?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, roleDTO.getRoleID());
        pstm.setString(2, roleDTO.getName());
        pstm.setString(3, roleDTO.getUserID());

        return pstm.executeUpdate() > 0 ? "Role Saved Successfully" : "Role Save Failed";
    }

    public String updateRole(RoleDTO roleDTO) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Role SET name=?, userID=? WHERE roleID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, roleDTO.getName());
        pstm.setString(2, roleDTO.getUserID());
        pstm.setString(3, roleDTO.getRoleID());

        return pstm.executeUpdate() > 0 ? "Role Updated Successfully" : "Role Update Failed";
    }

    public String deleteRole(String roleID) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Role WHERE roleID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, roleID);

        return pstm.executeUpdate() > 0 ? "Role Deleted Successfully" : "Role Delete Failed";
    }

    public RoleDTO searchRole(String roleID) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Role WHERE roleID=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, roleID);
        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new RoleDTO(
                    rst.getString("roleID"),
                    rst.getString("name"),
                    rst.getString("userID")
            );
        }
        return null;
    }

    public ArrayList<RoleDTO> getAllRoles() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Role";
        PreparedStatement pstm = conn.prepareStatement(sql);
        var rst = pstm.executeQuery();
        ArrayList<RoleDTO> allRoles = new ArrayList<>();
        while (rst.next()) {
            allRoles.add(new RoleDTO(
                    rst.getString("roleID"),
                    rst.getString("name"),
                    rst.getString("userID")
            ));
        }
        return allRoles;
    }
    
}
