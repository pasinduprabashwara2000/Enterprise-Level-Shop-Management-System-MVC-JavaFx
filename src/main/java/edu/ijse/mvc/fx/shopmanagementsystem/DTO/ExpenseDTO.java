package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseDTO {

    private String id;
    private String title;
    private String categoryType;
    private double amount;
    private String paymentMethod;
    private LocalDate date;

}
