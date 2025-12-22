package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDTO {

    private String paymentID;
    private String customerID;
    private String method;
    private double amount;
    private String reference;
    private LocalDate receivedAt;

}
