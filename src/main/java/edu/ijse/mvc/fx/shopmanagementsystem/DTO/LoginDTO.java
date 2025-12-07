package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {

    private String userName;
    private String password;
    private String role;

}
