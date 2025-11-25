package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnDTO {

    private String returnID;
    private String saleID;
    private String processedBy;
    private LocalDate returnDateTime;
    private String reason;
    private String status;

}
