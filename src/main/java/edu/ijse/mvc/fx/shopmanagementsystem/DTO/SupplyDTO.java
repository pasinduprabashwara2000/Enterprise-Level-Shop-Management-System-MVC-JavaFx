package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplyDTO {

    private String productID;
    private String supplierID;
    private double lastCost;
    private String supplierProductCode;

}
