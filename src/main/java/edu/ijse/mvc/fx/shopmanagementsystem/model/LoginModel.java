package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.LoginDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {

    public LoginDTO findByUsernameAndPassword(String username, String password) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT User.userName, User.password, Role.name\n" +
                "FROM User\n" +
                "JOIN Role\n" +
                "ON User.userID = Role.userID\n" +
                "WHERE User.userName = ? AND User.password = ?;";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,username);
        pstm.setString(2,password);
        ResultSet rst = pstm.executeQuery();

        if(rst.next()){
            LoginDTO loginDto = new LoginDTO(
            rst.getString("userName"),
            rst.getString("password"),
            rst.getString("name"));
            return loginDto;

        }

        return null;

    }

}
