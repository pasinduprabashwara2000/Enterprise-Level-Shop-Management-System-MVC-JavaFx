package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.SaleProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.util.CrudUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SaleModel {

    public String save(SaleDTO dto) throws Exception {

        Connection con = DBConnection.getInstance().getConnection();

        try {
            con.setAutoCommit(false);

            CrudUtil.execute(
                    "INSERT INTO sale (sale_date, customer_id, promotion_id, total_amount, discount, net_total) VALUES (?,?,?,?,?,?)",
                    java.sql.Date.valueOf(dto.getSaleDate()),
                    dto.getCustomerId(),
                    dto.getPromotionId(),
                    dto.getTotalAmount(),
                    dto.getDiscount(),
                    dto.getNetTotal()
            );

            ResultSet rs = CrudUtil.execute(
                    "SELECT sale_id FROM sale ORDER BY sale_id DESC LIMIT 1"
            );

            if (!rs.next()) {
                throw new RuntimeException("Failed to retrieve sale ID");
            }

            int saleId = rs.getInt("sale_id");

            for (SaleProductDTO sp : dto.getProducts()) {
                CrudUtil.execute(
                        "INSERT INTO SaleProduct (sale_id, product_id, qty) VALUES (?,?,?)",
                        saleId,
                        sp.getProductID(),
                        sp.getQyt()
                );
            }

            con.commit();
            return "Sale Saved Successfully";

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public String update(SaleDTO dto) throws Exception {

        return CrudUtil.execute(
                "UPDATE sale SET sale_date=?, customer_id=?, promotion_id=?, total_amount=?, discount=?, net_total=? WHERE sale_id=?",
                java.sql.Date.valueOf(dto.getSaleDate()),
                dto.getCustomerId(),
                dto.getPromotionId(),
                dto.getTotalAmount(),
                dto.getDiscount(),
                dto.getNetTotal(),
                dto.getSaleId()
        ) ? "Sale Updated Successfully" : "Sale Update Failed";
    }

    public String delete(int saleId) throws Exception {

        return CrudUtil.execute(
                "DELETE FROM sale WHERE sale_id=?",
                saleId
        ) ? "Sale Deleted Successfully" : "Sale Delete Failed";
    }

    public SaleDTO search(int saleId) throws Exception {

        ResultSet rs = CrudUtil.execute(
                "SELECT * FROM sale WHERE sale_id=?",
                saleId
        );

        if (rs.next()) {
            return new SaleDTO(
                    rs.getInt("sale_id"),
                    rs.getDate("sale_date").toLocalDate(),
                    rs.getString("customer_id"),
                    rs.getString("promotion_id"),
                    rs.getDouble("total_amount"),
                    rs.getDouble("discount"),
                    rs.getDouble("net_total"),
                    null
            );
        }
        return null;
    }

    public ArrayList<SaleProductTM> getAll() throws Exception {

        ResultSet rs = CrudUtil.execute("SELECT sale.sale_id, sale.customer_id, sale.promotion_id, SaleProduct.product_id , SaleProduct.qty ,sale.total_amount, sale.discount, sale.net_total, sale.sale_date\n" +
                "FROM sale\n" +
                "JOIN SaleProduct\n" +
                "ON sale.sale_id = SaleProduct.sale_id");

        ArrayList<SaleProductTM> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new SaleProductTM(
                    rs.getInt("sale_id"),
                    rs.getString("customer_id"),
                    rs.getString("product_id"),
                    rs.getString("promotion_id"),
                    rs.getInt("qty"),
                    rs.getDouble("total_amount"),
                    rs.getDouble("discount"),
                    rs.getDouble("net_total"),
                    rs.getDate("sale_date").toLocalDate()
            ));
        }
        return list;
    }
}
