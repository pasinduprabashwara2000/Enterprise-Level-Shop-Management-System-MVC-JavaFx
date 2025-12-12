package edu.ijse.mvc.fx.shopmanagementsystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CategoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.db.DBConnection;

public class CategoryModel {

    public String saveCategory(CategoryDTO categoryDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Category (name, description) VALUES(?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, categoryDTO.getName());
        pstm.setString(2, categoryDTO.getDescription());

        return pstm.executeUpdate() > 0 ? "Category Saved Successfully" : "Category Save Failed";

    }

    public String updateCategory(CategoryDTO categoryDTO) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Category SET name=?, description=? WHERE categoryID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, categoryDTO.getName());
        pstm.setString(2, categoryDTO.getDescription());
        pstm.setString(3, categoryDTO.getCategoryID());

        return pstm.executeUpdate() > 0 ? "Category Updated Successfully" : "Category Update Failed";

    }
    
    public String deleteCategory(String categoryID) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Category WHERE categoryID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, categoryID);

        return pstm.executeUpdate() > 0 ? "Category Deleted Successfully" : "Category Delete Failed";

    }

    public CategoryDTO searchCategory(String categoryID) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Category WHERE categoryID=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, categoryID);

        var rst = pstm.executeQuery();
        if (rst.next()) {
            return new CategoryDTO(
                    rst.getString("categoryID"),
                    rst.getString("name"),
                    rst.getString("description")
            );
        }
        return null;

    }

    public ArrayList<CategoryDTO> getAllCategories() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Category";
        PreparedStatement pstm = connection.prepareStatement(sql);

        var rst = pstm.executeQuery();
        ArrayList<CategoryDTO> categoryList = new ArrayList<>();
        while (rst.next()) {
            categoryList.add(new CategoryDTO(
                    rst.getString("categoryID"),
                    rst.getString("name"),
                    rst.getString("description")
            ));
        }
        return categoryList;
    }
    
}
