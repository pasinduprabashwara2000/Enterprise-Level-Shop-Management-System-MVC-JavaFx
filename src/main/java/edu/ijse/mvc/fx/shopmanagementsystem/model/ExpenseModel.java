package edu.ijse.mvc.fx.shopmanagementsystem.model;

import edu.ijse.mvc.fx.shopmanagementsystem.DB.DBConnection;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.ExpenseDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExpenseModel {

    public String saveExpense(ExpenseDTO expenseDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO expense (title,category_type,amount,payment_method,date) VALUES (?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,expenseDTO.getTitle());
        pstm.setString(2,expenseDTO.getCategoryType());
        pstm.setDouble(3,expenseDTO.getAmount());
        pstm.setString(4,expenseDTO.getPaymentMethod());
        pstm.setDate(5, Date.valueOf(expenseDTO.getDate()));

        return pstm.executeUpdate() > 0 ? "Expenses Save Successfully" : "Expenses Save Failed";

    }

    public String updateExpense(ExpenseDTO expenseDTO) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE expense SET title = ?, category_type = ?, amount = ?, payment_method = ?, date = ? WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,expenseDTO.getTitle());
        pstm.setString(2,expenseDTO.getCategoryType());
        pstm.setDouble(3,expenseDTO.getAmount());
        pstm.setString(4,expenseDTO.getPaymentMethod());
        pstm.setDate(5, Date.valueOf(expenseDTO.getDate()));
        pstm.setString(6,expenseDTO.getId());

        return pstm.executeUpdate() > 0 ? "Expense Update Successfully" : "Expense Update Failed";

    }

    public String deleteExpense(String id) throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM expense WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0 ? "Expense Delete Successfully" : "Expense Delete Failed";

    }

    public ExpenseDTO searchExpense(String id) throws Exception {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM expense WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet rst = pstm.executeQuery();

        if (rst.next()){
            ExpenseDTO expenseDTO = new ExpenseDTO(rst.getString("id"),
                    rst.getString("title"),
                    rst.getString("category_type"),
                    rst.getDouble("amount"),
                    rst.getString("payment_method"),
                    rst.getDate("date").toLocalDate());

            return expenseDTO;

        }
        return null;
    }

    public ArrayList<ExpenseDTO> getAllExpenses() throws Exception{

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM expense";
        PreparedStatement pstm = conn.prepareStatement(sql);

        ArrayList <ExpenseDTO> expenseDTOS = new ArrayList<>();
        ResultSet rst = pstm.executeQuery();

       while (rst.next()){
           expenseDTOS.add(new ExpenseDTO(
              rst.getString("id"),
              rst.getString("title"),
              rst.getString("category_type"),
              rst.getDouble("amount"),
              rst.getString("payment_method"),
              rst.getDate("date").toLocalDate()
           ));
       }

       return expenseDTOS;

    }

}
