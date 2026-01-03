package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnDTO {

    private String returnId;
    private String customerId;
    private double refundAmount;
    private String reason;
    private String action;
    private String status;
    private LocalDate returnDate;

}
