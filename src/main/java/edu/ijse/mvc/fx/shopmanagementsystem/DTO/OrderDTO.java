package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private String orderId;
    private String customerId;
    private LocalDate date;
    private double totalPrice;

}
