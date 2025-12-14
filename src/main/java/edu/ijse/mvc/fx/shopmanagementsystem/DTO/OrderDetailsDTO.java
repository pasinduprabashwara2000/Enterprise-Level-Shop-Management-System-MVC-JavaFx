package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsDTO {

    private String orderId;
    private String productId;
    private double unitPrice;
    private int qty;
    private double totalPrice;

}

