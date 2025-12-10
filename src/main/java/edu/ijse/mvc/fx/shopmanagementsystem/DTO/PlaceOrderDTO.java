package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO {

    private SaleDTO sale;
    private List<OrderItemDTO> items;
    private PaymentDTO payment;

}
