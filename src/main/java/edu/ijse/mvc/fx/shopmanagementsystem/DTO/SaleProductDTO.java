package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleProductDTO {

    private String saleProductID;
    private String saleID;
    private String productID;
    private String promotionID;
    private int quantity;
    private double unitPrice;
    private double lineDiscount;
    private double lineTax;
    private double lineTotal;
    
}
