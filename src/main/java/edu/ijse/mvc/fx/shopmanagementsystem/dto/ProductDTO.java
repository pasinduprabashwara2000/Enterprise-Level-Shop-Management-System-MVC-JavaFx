package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private String productID;
    private String SKU;
    private int barCode;
    private String name;
    private double unitPrice;
    private int qyt;
    private boolean active;
    private String categoryID;

}
