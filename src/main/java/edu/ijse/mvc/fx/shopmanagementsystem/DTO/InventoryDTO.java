package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventoryDTO {

    private String inventoryID;
    private String productID;
    private int reOrderLevel;
    private int reOrderQYT;
    private LocalDate lastStockUpdate;

}
