package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDTO {
    
    private String supplierID;
    private String name;
    private String contactPerson;
    private int phone;
    private String email;
    private String address;

}
