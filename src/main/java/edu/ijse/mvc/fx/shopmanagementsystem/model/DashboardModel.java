package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.DB.DBConnection;

public class DashboardModel {

    public static int getProductsCount() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS total_products FROM Product";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_products");
        }
        return 0;
    }

    public static int getCustomerCount() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS total_customers FROM Customer";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_customers");
        }
        return 0;
    }

    public static int getSupplierCount() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS total_suppliers FROM Supplier";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_suppliers");
        }
        return 0;
    }

    public static int getInventoryCount() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(qyt) AS total_inventory FROM Product";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_inventory");
        }
        return 0;
    }

    public static int getOrderCount() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS total_orders FROM orders";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_orders");
        }
        return 0;
    }

    public static int getPaymentCount() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS total_payment FROM Payment";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_payment");
        }
        return 0;
    }

    public static int getReturnCount() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(*) AS total_return FROM Returns";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("total_return");
        }
        return 0;
    }

    public static ArrayList<ProductTM> topSellingProducts() throws Exception {

        ArrayList<ProductTM> products = new ArrayList<>();
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = """
        
                SELECT
                    p.productID,
                    p.name,
                    p.SKU,
                    p.barCode,
                    SUM(op.qty) AS total_qty,
                    p.unitPrice,
                    p.categoryID
                FROM Product p
                JOIN order_products op
                    ON p.productID = op.product_id
                GROUP BY
                    p.productID,
                    p.name,
                    p.SKU,
                    p.barCode,
                    p.unitPrice,
                    p.categoryID
                ORDER BY total_qty DESC
                LIMIT 10;
                
        """;

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();

        while (rst.next()) {
            products.add(new ProductTM(
                    rst.getString("productID"),
                    rst.getString("name"),
                    rst.getString("SKU"),
                    rst.getString("barCode"),
                    rst.getInt("total_qty"),
                    rst.getDouble("unitPrice"),
                    rst.getString("categoryID")
            ));
        }

        return products;
    }

    public static double calculateTotalRevenue() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT SUM(amount) AS revenue_total FROM Payment";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getDouble("revenue_total");
        }
        return 0.0;
    }

    public static double calculateTotalProfit() throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sqlRevenue = "SELECT SUM(amount) AS total_revenue FROM Payment";
        String sqlRefund = "SELECT SUM(refundAmount) AS total_refund FROM Returns";

        PreparedStatement pstRevenue = conn.prepareStatement(sqlRevenue);
        PreparedStatement pstRefund = conn.prepareStatement(sqlRefund);

        ResultSet rstRevenue = pstRevenue.executeQuery();
        ResultSet rstRefund = pstRefund.executeQuery();

        double totalRevenue = rstRevenue.next() ? rstRevenue.getDouble("total_revenue") : 0.0;
        double totalRefund = rstRefund.next() ? rstRefund.getDouble("total_refund") : 0.0;

        return totalRevenue - totalRefund;
    }
}
