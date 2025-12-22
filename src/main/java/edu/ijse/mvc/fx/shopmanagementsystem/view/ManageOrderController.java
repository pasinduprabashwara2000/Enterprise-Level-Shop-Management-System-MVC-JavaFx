package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.CustomerDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.OrderProductTM;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.ProductDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.OrderProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

public class ManageOrderController {

    final private CustomerController customerController = new CustomerController();
    final private ProductController productController = new ProductController();
    final OrderProductModel orderProductModel = new OrderProductModel();

    @FXML
    private Button addItemBtn;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<OrderProductTM, String> colProductId;

    @FXML
    private TableColumn<OrderProductTM, String> colProductName;

    @FXML
    private TableColumn<OrderProductTM, Integer> colQuantity;

    @FXML
    private TableColumn<OrderProductTM, Double> colTotal;

    @FXML
    private TableColumn<OrderProductTM, Double> colUnitPrice;

    @FXML
    private ComboBox<String> customerIdCombo;

    @FXML
    private TextField discountTxt;

    @FXML
    private TextField netAmountTxt;

    @FXML
    private DatePicker orderDatePicker;

    @FXML
    private TableView<OrderProductTM> orderItemsTable;

    @FXML
    private ComboBox<String> productIdCombo;

    @FXML
    private TextField quantityTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveOrderBtn;

    @FXML
    private TextField totalAmountTxt;

    @FXML
    private TextField unitPriceTxt;

    @FXML
    void initialize() throws Exception {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadCustomerIdThread();
        loadProductIdThread();
        loadData();
    }

    private void loadCustomerIdThread() throws Exception{

        Task <ObservableList<String>> task  = new Task<>() {

            ArrayList <CustomerDTO> customers = customerController.getAllCustomers();
            @Override
            protected ObservableList<String> call() throws Exception {
                return FXCollections.observableArrayList(customers.stream().map(CustomerDTO::getCustomerId).toList());
            }
        };
        task.setOnSucceeded(event -> customerIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR, task.getMessage()).show());
        new Thread(task).start();
    }

    private void loadProductIdThread() throws Exception {
        Task <ObservableList<String>> task = new Task() {

            ArrayList <ProductDTO> products = productController.getAllProducts();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(products.stream().map(ProductDTO::getProductID).toList());
            }
        };
        task.setOnSucceeded(event -> productIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    private void loadTable(){
        try {
            orderItemsTable.getItems().clear();
            orderItemsTable.getItems().addAll(orderProductModel.getAll());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadData(){
        try {

        } catch (Exception e) {

        }
    }

    @FXML
    void addItemToOrder(ActionEvent event) {

    }

    @FXML
    void resetForm(ActionEvent event) {

    }

    @FXML
    void saveOrder(ActionEvent event) {

    }

}
