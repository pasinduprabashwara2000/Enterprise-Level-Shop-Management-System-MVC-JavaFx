package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private int orderId;
    private String customerId;
    private LocalDate orderDate;
    List<OrderProductDTO> orderProducts;

}
