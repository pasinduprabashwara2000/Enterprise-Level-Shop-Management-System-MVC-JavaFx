package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleDTO {

    private String saleID;
    private String customerID;
    private LocalDate saleDate;
    private double totalAmount;
    private double discount;
    private double netTotal;

}
