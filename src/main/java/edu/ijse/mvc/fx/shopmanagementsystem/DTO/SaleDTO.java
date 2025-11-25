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
    private String userID;
    private String customerID;
    private double subTotal;
    private double taxTotal;
    private double discountTotal;
    private double grandTotal;
    private LocalDate date;
    private String status;

}
