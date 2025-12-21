package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnProductDTO {

    private String returnID;
    private String productID;
    private int qty;
    private double refundAmount;
    private String action;

}
