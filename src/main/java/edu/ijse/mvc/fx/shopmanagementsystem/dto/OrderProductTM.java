package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProductTM {

    private String productID;
    private String name;
    private int qty;
    private double unitPrice;
    private double total;

}
