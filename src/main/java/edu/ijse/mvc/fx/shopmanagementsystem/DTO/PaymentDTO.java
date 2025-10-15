package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

    private String paymentID;
    private String saleID;
    private Method method;
    private double amount;
    private String reference;
    private Date receivedAt;

    public enum Method {
        CASH, CARD, MOBILE, OTHER
    }
    
}
