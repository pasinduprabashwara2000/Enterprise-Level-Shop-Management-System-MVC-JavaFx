package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import java.time.LocalDate;
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
    private String active;
    private LocalDate createdAt;

}
