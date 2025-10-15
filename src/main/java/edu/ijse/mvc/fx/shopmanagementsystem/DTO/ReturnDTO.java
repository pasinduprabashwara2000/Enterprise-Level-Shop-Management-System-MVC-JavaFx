package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnDTO {

    private String returnID;
    private String saleID;
    private String processedBy;
    private Date returnDateTime;
    private String reason;
    private Status status;

    public enum Status {
        PENDING,APPROVED,REJECTED,COMPLETED
    }
    
}
