package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.UserDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.UserModel;

public class UserController {

    private final UserModel userModel = new UserModel();

    public String saveUser(UserDTO userDTO) throws Exception {
        return userModel.saveUser(userDTO);
    }

    public String updateUser(UserDTO userDTO) throws Exception {
        return userModel.updateUser(userDTO);
    }

    public String deleteUser(String userID) throws Exception {
        return userModel.deleteUser(userID);
    }

    public UserDTO searchUser(String userID) throws Exception {
        return userModel.searchUser(userID);
    }

    public ArrayList<UserDTO> getAllUsers() throws Exception {
        return userModel.getAllUsers();
    }
    
}
