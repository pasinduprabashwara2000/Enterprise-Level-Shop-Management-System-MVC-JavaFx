package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaceOrderProductDTO {

    private String poItemId;
    private String poId;
    private int quantityOrdered;
    private int quantityReceived;
    private double unitCost;
    private double lineTotal;

}