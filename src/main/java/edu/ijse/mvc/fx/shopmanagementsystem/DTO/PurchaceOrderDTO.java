package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaceOrderDTO {

    private String poId;
    private String supplierId;
    private LocalDate createdAt;
    private String createdBy;
    private String status;
    private LocalDate expectedDate;
    private Double totalCost;

}
