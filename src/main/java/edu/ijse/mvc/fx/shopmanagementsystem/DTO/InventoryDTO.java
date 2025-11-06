package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryDTO {

    private String productID;
    private int QYT;
    private int reOrderLevel;
    private int reOrderQYT;
    private LocalDate lastStockUpdate;

}