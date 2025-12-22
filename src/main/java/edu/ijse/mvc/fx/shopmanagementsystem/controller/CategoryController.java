package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.CategoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.CategoryModel;

public class CategoryController {
    
    private final CategoryModel categoryModel = new CategoryModel();

    public String saveCategory(CategoryDTO categoryDTO) throws Exception{
        return categoryModel.saveCategory(categoryDTO);
    }

    public String updateCategory(CategoryDTO categoryDTO) throws Exception{
        return categoryModel.updateCategory(categoryDTO);
    }

    public String deleteCategory(String categoryID) throws Exception{
        return categoryModel.deleteCategory(categoryID);
    }

    public CategoryDTO searchCategory(String categoryID) throws Exception{
        return categoryModel.searchCategory(categoryID);
    }

    public ArrayList<CategoryDTO> getAllCategories() throws Exception{
        return categoryModel.getAllCategories();
    }
    
}
