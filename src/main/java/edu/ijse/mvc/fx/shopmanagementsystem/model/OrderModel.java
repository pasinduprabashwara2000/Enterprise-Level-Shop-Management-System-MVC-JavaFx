package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.dto.OrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.util.CrudUtil;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.ResultSet;

public class OrderModel {

    private OrderProductModel orderProductModel = new OrderProductModel();

    public int placeOrder(OrderDTO orderDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);
            boolean isOrderSaved = CrudUtil.execute("INSERT INTO orders (date, customerID) VALUES (?,?)",
                    orderDTO.getOrderDate(),
                    orderDTO.getCustomerId());

            if(isOrderSaved){
                ResultSet rst = CrudUtil.execute("SELECT orderID FROM orders ORDER BY orderID DESC LIMIT 1");
                if(rst.next()){
                    int latestOrderId = rst.getInt("orderID");
                    orderProductModel.saveOrderProducts(latestOrderId, orderDTO.getOrderProducts());
                } else {
                    throw new Exception("Something Went Wrong");
                }
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            conn.setAutoCommit(true);
        }
        return 0;
    }

}
