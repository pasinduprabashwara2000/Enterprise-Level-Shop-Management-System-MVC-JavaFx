package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.LoginDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.LoginModel;

public class LoginController {

    private final LoginModel loginModel = new LoginModel();

    public LoginDTO login(LoginDTO loginDTO) throws Exception{
        return loginModel.findByUsernameAndPassword(loginDTO);
    }

}
