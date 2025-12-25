package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.OrderProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.OrderProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderProductModel {

    final ProductModel productModel = new ProductModel();

    public boolean saveOrderProducts(int orderId, List<OrderProductDTO> list) throws Exception {

        for (OrderProductDTO dto : list) {

            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO order_product(order_id, product_id, qty, price, total) VALUES (?,?,?,?,?)",
                    orderId,
                    dto.getProductId(),
                    dto.getQty(),
                    dto.getPrice(),
                    dto.getTotal()
            );

            if (!isSaved) {
                throw new Exception("Failed to save order product");
            }

            boolean isQtyUpdated = productModel.decreaseProductQYT(
                    dto.getProductId(),
                    dto.getQty()
            );

            if (!isQtyUpdated) {
                throw new Exception("Failed to update product quantity");
            }
        }

        return true;
    }

    public ArrayList<OrderProductTM> getAll() throws Exception{

        ArrayList<OrderProductTM> products = new ArrayList<>();

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT Product.productID, Product.name, Product.unitPrice, order_product.qty, order_product.total\n" +
                "FROM Product\n" +
                "JOIN order_product\n" +
                "ON Product.productID = order_product.product_id";
        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();
        while (rst.next()){
            OrderProductTM orderProductTM = new OrderProductTM(
                    rst.getString("productID"),
                    rst.getString("name"),
                    rst.getInt("qty"),
                    rst.getDouble("unitPrice"),
                    rst.getDouble("total"));
                    products.add(orderProductTM);
        }
        return products;
    }

}
