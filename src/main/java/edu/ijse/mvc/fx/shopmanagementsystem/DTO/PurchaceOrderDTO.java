package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaceOrderDTO {

    private String poId;
    private String supplierId;
    private Date createdAt;
    private String createdBy;
    private Status status;
    private Date expectedDate;
    private Double totalCost;

    public enum Status {
        DRAFT, SENT, RECEIVED, CANCELLED, PARTIAL
    }
    
}
