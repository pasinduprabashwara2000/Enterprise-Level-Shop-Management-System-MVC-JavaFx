package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleProductTM {

    private int saleId;
    private String customerId;
    private String productID;
    private String promotionId;
    private int qyt;
    private double totalAmount;
    private double discount;
    private double netTotal;
    private LocalDate saleDate;

}
