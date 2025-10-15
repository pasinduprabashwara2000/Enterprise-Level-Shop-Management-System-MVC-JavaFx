package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.sql.Date;
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
    private Date date;
    private Status status;

    public enum Status {
        COMPLETED, PENDING, CANCELLED
    }

}
