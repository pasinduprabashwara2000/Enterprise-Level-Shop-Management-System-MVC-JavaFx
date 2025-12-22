package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseDTO {

    private String expenseId;
    private LocalDate expenseDate;
    private String expenseType;
    private double amount;
    private String paymentMethod;

}

