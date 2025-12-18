package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnProductDTO {

    private String returnItemId;
    private String returnId;
    private String productId;
    private int saleItemId;
    private int quantity;
    private double refundAmount;
    private String action;

}
