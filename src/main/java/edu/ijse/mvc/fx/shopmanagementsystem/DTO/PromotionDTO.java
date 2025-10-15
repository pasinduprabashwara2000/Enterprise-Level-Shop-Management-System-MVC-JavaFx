package edu.ijse.mvc.fx.shopmanagementsystem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionDTO {

    private String promoteID;
    private String name;
    private Type type;
    private Double value;
    private Date startAt;
    private Date endAt;
    private boolean active;
    private String categoryID;
    private String productID;

    public enum Type {
        PERCENT, AMOUNT
    }
    
}