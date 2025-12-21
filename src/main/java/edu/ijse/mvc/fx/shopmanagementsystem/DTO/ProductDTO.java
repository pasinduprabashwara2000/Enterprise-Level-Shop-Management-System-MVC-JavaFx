package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

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
    private String unit;
    private double unitPrice;
    private boolean active;
    private String categoryID;

}
