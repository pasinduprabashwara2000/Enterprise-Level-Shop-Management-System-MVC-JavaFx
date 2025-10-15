package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    
    private String userID;
    private String userName;
    private String password;
    private String roleID;
    private boolean active;
    private Date createdAt;

}
