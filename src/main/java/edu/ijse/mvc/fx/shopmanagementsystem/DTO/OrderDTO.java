package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private double totalAmount;
    private double discount;
    private double netAmount;
    private List<OrderItemDTO> items;

}
