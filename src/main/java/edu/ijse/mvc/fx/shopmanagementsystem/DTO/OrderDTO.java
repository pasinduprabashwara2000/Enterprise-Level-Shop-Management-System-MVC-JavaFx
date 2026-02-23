package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private int orderId;
    private String customerId;
    private Date orderDate;
    List<OrderProductDTO> orderItems;

    public OrderDTO(String customerId, Date orderDate, List<OrderProductDTO> orderItems){
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
    }

}


