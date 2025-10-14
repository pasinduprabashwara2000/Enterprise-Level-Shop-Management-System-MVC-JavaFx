package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.CustomerModel;

class CustomerController {

    final private CustomerModel customerModel = new CustomerModel();

    public String saveCustomer(CustomerDTO customerDTO) throws Exception {
        return customerModel.saveCustomer(customerDTO);
    }

    public String updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerModel.updateCustomer(customerDTO);
    }

    public String deleteCustomer(String customerID) throws Exception {
        return customerModel.deleteCustomer(customerID);
    }

    public CustomerDTO searchCustomer(String customerID) throws Exception {
        return customerModel.searchCustomer(customerID);
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        return customerModel.getAllCustomers();
    }

}