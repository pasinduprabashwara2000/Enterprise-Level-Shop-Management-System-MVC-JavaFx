package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.RoleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.RoleModel;

public class RoleController {

    private final RoleModel roleModel = new RoleModel();

    public String saveRole(RoleDTO roleDTO) throws Exception {
        return roleModel.saveRole(roleDTO);
    }

    public String updateRole(RoleDTO roleDTO) throws Exception {
        return roleModel.updateRole(roleDTO);
    }

    public String deleteRole(String roleID) throws Exception {
        return roleModel.deleteRole(roleID);
    }

    public RoleDTO searchRole(String roleID) throws Exception {
        return roleModel.searchRole(roleID);
    }

    public ArrayList<RoleDTO> getAllRoles() throws Exception {
        return roleModel.getAllRoles();
    }
    
}
