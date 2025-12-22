package edu.ijse.mvc.fx.shopmanagementsystem.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionDTO {

    private String promotionID;
    private String name;
    private String type;
    private Double value;
    private Date startAt;
    private Date endAt;
    private boolean active;

}