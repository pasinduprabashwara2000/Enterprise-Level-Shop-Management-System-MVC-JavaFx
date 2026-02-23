package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.OrderProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.util.CrudUtil;
import java.util.List;

public class OrderProductModel {

    final ProductModel productModel = new ProductModel();

    public boolean saveProductsToList(int orderId, List<OrderProductDTO> dtoList) throws Exception {

        for (OrderProductDTO orderProductDTO : dtoList) {

            boolean isInserted = CrudUtil.execute(
                    "INSERT INTO order_products (order_id, product_id, qty, price) VALUES (?,?,?,?)",
                    orderId,
                    orderProductDTO.getProductId(),
                    orderProductDTO.getQty(),
                    orderProductDTO.getPrice()
            );

            if (!isInserted) {
                throw new Exception("Something Went Wrong Inserting Data");
            }

            boolean isQtyUpdated = productModel.decreaseProductQTY(
                    orderProductDTO.getProductId(),
                    orderProductDTO.getQty()
            );

            if (!isQtyUpdated) {
                throw new Exception("Something Went Wrong Reducing QTY");
            }
        }

        return true;
    }
}
