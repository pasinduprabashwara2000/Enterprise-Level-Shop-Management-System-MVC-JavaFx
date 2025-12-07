package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.LoginDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {

    public LoginDTO findByUsernameAndPassword(LoginDTO loginDTO) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT u.username, u.password, r.name AS role\n" +
                "FROM User u\n" +
                "JOIN Role r ON u.userID = r.userID\n" +
                "WHERE u.username = ? \n" +
                "  AND u.password = ?\n" +
                "  AND r.name = ?;\n";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,loginDTO.getUserName());
        pstm.setString(2,loginDTO.getPassword());
        pstm.setString(3,loginDTO.getRole());

        ResultSet rst = pstm.executeQuery();

        if(rst.next()){
            LoginDTO loginDto = new LoginDTO(
            rst.getString("userName"),
            rst.getString("password"),
            rst.getString("role"));

            return loginDto;

        }

        return null;

    }

}
