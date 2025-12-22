package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ProductModel;

public class ProductController {

    private final ProductModel productModel = new ProductModel(); 

    public String saveProduct(ProductDTO productDTO) throws Exception{
        return productModel.saveProduct(productDTO);
    }

    public String updateProduct(ProductDTO productDTO) throws Exception{
        return productModel.updateProduct(productDTO);
    }

    public String deleteProduct(String productID) throws Exception{
        return productModel.deleteProduct(productID);
    }

    public ProductDTO searchProduct(String productID) throws Exception{
        return productModel.searchProduct(productID);
    }

    public ArrayList<ProductDTO> getAllProducts() throws Exception{
        return productModel.getAllProducts();
    }
    
}
