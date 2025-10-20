package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.CategoryDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CategoryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageCategoryController {

    private final CategoryController categoryController = new CategoryController();

    @FXML
    private Label categoryIDLabel;

    @FXML
    private TextField categoryIDTxt;

    @FXML
    private TableColumn<CategoryDTO, String> colCategoryId;

    @FXML
    private TableColumn<CategoryDTO, String> colDescription;

    @FXML
    private TableColumn<CategoryDTO, String> colName;

    @FXML
    private Button deleteBtn;

    @FXML
    private Label descriptionLabel;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TableView<CategoryDTO> detailsTable;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    public void initialize(){
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadTable();
    }

    private void loadTable() {
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(categoryController.getAllCategories());
        } catch (Exception e) {
            new Alert(AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateDelete(ActionEvent event) {
        try {
            String res = categoryController.deleteCategory(categoryIDTxt.getText());
            new Alert(AlertType.INFORMATION,res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void navigateReset(ActionEvent event) {
        categoryIDTxt.clear();
        nameTxt.clear();
        descriptionTxt.clear();
    }

    @FXML
    void navigateSave(ActionEvent event) {
        try{
            CategoryDTO categoryDTO = new CategoryDTO(
                    categoryIDTxt.getText(),
                    nameTxt.getText(),
                    descriptionTxt.getText()
            );
            String res = categoryController.saveCategory(categoryDTO);
            new Alert(Alert.AlertType.INFORMATION,res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void navigateUpdate(ActionEvent event) {
        try {
            CategoryDTO categoryDTO = new CategoryDTO(
                categoryIDTxt.getText(),
                nameTxt.getText(),
                descriptionTxt.getText()
            );
            String res = categoryController.updateCategory(categoryDTO);
            new Alert(AlertType.INFORMATION,res).show();
            loadTable();
            navigateReset(event);
        } catch (Exception e){
            new Alert(AlertType.ERROR,e.getMessage()).show();
        }
    }

}
