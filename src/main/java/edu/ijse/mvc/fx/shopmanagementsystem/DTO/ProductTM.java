package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductTM {

    private String productID;
    private String name;
    private String SKU;
    private String barCode;
    private int totalQty;
    private double unitPrice;
    private String categoryID;

}
