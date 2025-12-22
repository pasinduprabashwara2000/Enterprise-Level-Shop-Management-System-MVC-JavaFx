package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {

    private String customerId;
    private String name;
    private int phone;
    private String email;
    private String loyaltyCode;

}
