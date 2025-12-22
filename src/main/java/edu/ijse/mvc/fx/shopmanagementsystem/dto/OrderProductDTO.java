package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProductDTO {

    private int orderProductId;
    private int productId;
    private int qty;
    private double price;

    public double getTotal(){
        return qty * price;
    }

}
