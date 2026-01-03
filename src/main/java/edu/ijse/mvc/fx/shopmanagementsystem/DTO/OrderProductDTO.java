package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProductDTO {

    private int orderId;
    private String productId;
    private int qty;
    private double price;

    public OrderProductDTO(String productId, double price, int qty) {
        this.productId = productId;
        this.price = price;
        this.qty = qty;
    }

}
