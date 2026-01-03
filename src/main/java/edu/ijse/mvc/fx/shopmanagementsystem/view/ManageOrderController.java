package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.*;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Date;

public class ManageOrderController {

    final private CustomerController customerController = new CustomerController();
    final private ProductController productController = new ProductController();
    private OrderModel orderModel = new OrderModel();

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private TableColumn<OrderProductTM, Void> colAction;

    @FXML
    private TableColumn<OrderProductTM, String> colItemName;

    @FXML
    private TableColumn<OrderProductTM, Integer> colQty;

    @FXML
    private TableColumn<OrderProductTM, Double> colTotalPrice;

    @FXML
    private TableColumn<OrderProductTM, Double> colUnitPrice;

    @FXML
    private ComboBox<String> comboCustomerId;

    @FXML
    private ComboBox<String> comboItemId;

    @FXML
    private Label lblCustomerPhoneValue;

    @FXML
    private Label lblCustomerNameValue;

    @FXML
    private Label lblCustomerEmailValue;

    @FXML
    private Label lblItemNameValue;

    @FXML
    private Label lblItemPriceValue;

    @FXML
    private Label lblItemQtyValue;

    @FXML
    private Label lblOrderTotal;

    @FXML
    private TextField qtyField;

    @FXML
    private TableView<OrderProductTM> tblOrderItem;

    private final ObservableList<OrderProductTM> orderProductTMS = FXCollections.observableArrayList();

    @FXML
    void initialize() throws Exception {
        colItemName.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("productTotal"));

        loadCustomerIdThread();
        loadItemIdThread();
    }

    private void loadCustomerIdThread() throws Exception{
        Task <ObservableList<String>> task = new Task<>() {

            ArrayList <CustomerDTO> customers = customerController.getAllCustomers();
            @Override
            protected ObservableList<String> call() throws Exception {
                return FXCollections.observableArrayList(customers.stream().map(CustomerDTO::getCustomerId).toList());
            }
        };
        task.setOnSucceeded(event -> comboCustomerId.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    @FXML
    void selectedCustomerId(ActionEvent event) throws Exception {

        try {
            String selectedId = comboCustomerId.getSelectionModel().getSelectedItem();
            if (selectedId != null) {
                CustomerDTO customerDTO = customerController.searchCustomer(selectedId);
                lblCustomerNameValue.setText(customerDTO.getName());
                lblCustomerPhoneValue.setText(String.valueOf(customerDTO.getPhone()));
                lblCustomerEmailValue.setText(customerDTO.getEmail());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void loadItemIdThread() throws Exception{
        Task<ObservableList<String>> task = new Task<>() {

            ArrayList <ProductDTO> products = productController.getAllProducts();
            @Override
            protected ObservableList<String> call() throws Exception {
                return FXCollections.observableArrayList(products.stream().map(ProductDTO::getProductID).toList());
            }
        };
        task.setOnSucceeded(event -> comboItemId.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR,task.getMessage()).show());
        new Thread(task).start();
    }

    @FXML
    void selectedItemId(ActionEvent event) {
        try {
            String selectedId = comboItemId.getSelectionModel().getSelectedItem();
            ProductDTO productDTO = productController.searchProduct(selectedId);

            if(productDTO != null){
                lblItemNameValue.setText(productDTO.getName());
                lblItemPriceValue.setText(String.valueOf(productDTO.getUnitPrice()));
                lblItemQtyValue.setText(String.valueOf(productDTO.getQyt()));
            }
        } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void handleAddToCart(ActionEvent event) {
        String itemId = comboItemId.getSelectionModel().getSelectedItem();
        String itemName = lblItemNameValue.getText();
        String itemQYT = lblItemQtyValue.getText();
        String itemPrice = lblItemPriceValue.getText();
        String orderQYT = qtyField.getText();

        if(itemId != null){
            if (orderQYT != null){
                if (Integer.parseInt(orderQYT) <= (Integer.parseInt(itemQYT))){
                    OrderProductTM orderProductTM = new OrderProductTM(
                            itemId,
                            itemName,
                            Integer.parseInt(itemQYT),
                            Double.parseDouble(itemPrice),
                            Integer.parseInt(itemQYT)*Double.parseDouble(itemPrice)
                    );

                    orderProductTMS.add(orderProductTM);
                    loadTable();

                } else {
                   new Alert(Alert.AlertType.ERROR,"Invalid").show();
                }

                } else {
                    new Alert(Alert.AlertType.ERROR,"Invalid Quantity").show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Item Id").show();
        }

    }

    void loadTable() {
        tblOrderItem.setItems(orderProductTMS);

        double total = 0.0;

        for (OrderProductTM orderProductTM : orderProductTMS){
            total += orderProductTM.getProductTotal();
        }

        lblOrderTotal.setText(String.valueOf(total));

    }

    @FXML
    void handlePlaceOrder(ActionEvent event) {
        try {
            String customerId = comboCustomerId.getSelectionModel().getSelectedItem();

            ArrayList <OrderProductDTO> orderProductDTOS = new ArrayList<>();

            for (OrderProductTM orderProductTM : orderProductTMS){
                OrderProductDTO orderProductDTO = new OrderProductDTO(
                        orderProductTM.getProductId(),
                        orderProductTM.getUnitPrice(),
                        orderProductTM.getOrderQty()

                );
                orderProductDTOS.add(orderProductDTO);
            }

            OrderDTO orderDTO = new OrderDTO(customerId, new Date(), orderProductDTOS);
            boolean isOrderPlaced = orderModel.placeOrder(orderDTO);

            if (isOrderPlaced){
                new Alert(Alert.AlertType.INFORMATION,"Order Placed Successfully").show();
                orderModel.printInvoice(isOrderPlaced);
            } else {
                new Alert(Alert.AlertType.ERROR,"Order Saved Failed").show();
            }

        } catch (Exception e) {
            new Alert (Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
