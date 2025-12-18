package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.LoginDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.LoginModel;

public class LoginController {

    private final LoginModel loginModel = new LoginModel();

    public LoginDTO login(String username, String password) throws Exception{
        return loginModel.findByUsernameAndPassword(username, password);
    }

}
