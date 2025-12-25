package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseOrderDTO {

    private String poId;
    private String supplierId;
    private Double totalCost;
    private String status;
    private LocalDate createdAt;
    private LocalDate expectedDate;

}
