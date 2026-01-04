package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DB.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.OrderDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderModel {

    private OrderProductModel orderProductModel = new OrderProductModel();

    public int placeOrder(OrderDTO orderDTO) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);

            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO orders (date, customer_id) VALUES (?, ?)",
                    orderDTO.getOrderDate(),
                    orderDTO.getCustomerId()
            );

            if (!isSaved) {
                throw new Exception("Error Inserting Order Data");
            }

            ResultSet rst = CrudUtil.execute(
                    "SELECT id FROM orders ORDER BY id DESC LIMIT 1"
            );

            if (!rst.next()) {
                throw new Exception("Error Finding Order ID");
            }

            int orderId = rst.getInt("id");

            boolean isItemsSaved = orderProductModel.saveProductsToList(
                    orderId,
                    orderDTO.getOrderItems()
            );

            if (!isItemsSaved) {
                throw new Exception("Error Saving Order Items");
            }

            conn.commit();
            return orderId;

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            return -1;

        } finally {
            conn.setAutoCommit(true);
        }
    }

    public void printInvoice(Integer orderId) throws SQLException, JRException, ClassNotFoundException {

        Connection conn = DBConnection.getInstance().getConnection();
        InputStream inputStream = getClass().getResourceAsStream("/edu/ijse/mvc/fx/shopmanagementsystem/reports/invoice.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderId);

        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn);
        JasperViewer.viewReport(jp, false);
    }

}
