package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.*;
import edu.ijse.mvc.fx.shopmanagementsystem.model.CustomerModel;
import edu.ijse.mvc.fx.shopmanagementsystem.model.OrderModel;
import edu.ijse.mvc.fx.shopmanagementsystem.model.ProductModel;
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

    private final CustomerModel customerModel = new CustomerModel();
    private final ProductModel productModel = new ProductModel();
    private final OrderModel orderModel = new OrderModel();

    @FXML
    private Button resetBtn;

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

    @FXML
    private TableColumn<OrderProductTM,String> colItemName;

    @FXML
    private TableColumn<OrderProductTM,Integer> colQty;

    @FXML
    private TableColumn<OrderProductTM,Double> colUnitPrice;

    @FXML
    private TableColumn<OrderProductTM,Double> colTotalPrice;

    @FXML
    private TableColumn<OrderProductTM,Void> colAction;

    private final ObservableList<OrderProductTM> orderProductTMS = FXCollections.observableArrayList();

    @FXML
    void initialize() throws Exception {

        colItemName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("productTotal"));

        addDeleteButton();

        loadCustomerIdThread();
        loadItemIdThread();
    }

    // Delete Button inside Table
    private void addDeleteButton(){

        colAction.setCellFactory(param -> new TableCell<>() {

            private final Button btnDelete = new Button("Delete");

            {
                btnDelete.setOnAction(event -> {

                    OrderProductTM selectedItem =
                            getTableView().getItems().get(getIndex());

                    orderProductTMS.remove(selectedItem);
                    loadTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if(empty){
                    setGraphic(null);
                }else{
                    setGraphic(btnDelete);
                }
            }
        });
    }

    // Load Customer IDs
    private void loadCustomerIdThread() {

        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {

                ArrayList<CustomerDTO> customers =
                        customerModel.getAllCustomers();

                return FXCollections.observableArrayList(
                        customers.stream()
                                .map(CustomerDTO::getCustomerId)
                                .toList()
                );
            }
        };

        task.setOnSucceeded(e -> comboCustomerId.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR,
                task.getException().getMessage()).show());

        new Thread(task).start();
    }

    // Customer Selection
    @FXML
    void selectedCustomerId(ActionEvent event) {

        try {

            String selectedId =
                    comboCustomerId.getSelectionModel().getSelectedItem();

            if(selectedId != null){

                CustomerDTO customerDTO =
                        customerModel.searchCustomer(selectedId);

                lblCustomerNameValue.setText(customerDTO.getName());
                lblCustomerPhoneValue.setText(String.valueOf(customerDTO.getPhone()));
                lblCustomerEmailValue.setText(customerDTO.getEmail());
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    // Load Item IDs
    private void loadItemIdThread(){

        Task<ObservableList<String>> task = new Task<>() {
            @Override
            protected ObservableList<String> call() throws Exception {

                ArrayList<ProductDTO> products =
                        productModel.getAllProducts();

                return FXCollections.observableArrayList(
                        products.stream()
                                .map(ProductDTO::getProductID)
                                .toList()
                );
            }
        };

        task.setOnSucceeded(e -> comboItemId.setItems(task.getValue()));
        task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR,
                task.getException().getMessage()).show());

        new Thread(task).start();
    }

    // Item Selection
    @FXML
    void selectedItemId(ActionEvent event) {

        try {

            String selectedId =
                    comboItemId.getSelectionModel().getSelectedItem();

            ProductDTO productDTO =
                    productModel.searchProduct(selectedId);

            if(productDTO != null){

                lblItemNameValue.setText(productDTO.getName());
                lblItemPriceValue.setText(String.valueOf(productDTO.getUnitPrice()));
                lblItemQtyValue.setText(String.valueOf(productDTO.getQyt()));
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    // Add to Cart
    @FXML
    void handleAddToCart(ActionEvent event) {

        String itemId = comboItemId.getValue();
        String itemName = lblItemNameValue.getText();
        String itemQty = lblItemQtyValue.getText();
        String itemPrice = lblItemPriceValue.getText();
        String orderQty = qtyField.getText();

        if(itemId == null){
            new Alert(Alert.AlertType.ERROR,"Select Item").show();
            return;
        }

        if(orderQty == null || orderQty.isBlank()){
            new Alert(Alert.AlertType.ERROR,"Invalid Quantity").show();
            return;
        }

        int orderQuantity = Integer.parseInt(orderQty);
        int availableQty = Integer.parseInt(itemQty);

        if(orderQuantity > availableQty){
            new Alert(Alert.AlertType.ERROR,"Not enough stock").show();
            return;
        }

        OrderProductTM tm = new OrderProductTM(
                itemId,
                itemName,
                orderQuantity,
                Double.parseDouble(itemPrice),
                orderQuantity * Double.parseDouble(itemPrice)
        );

        orderProductTMS.add(tm);

        loadTable();
    }

    // Load Table
    void loadTable(){

        tblOrderItem.setItems(orderProductTMS);

        double total = 0;

        for(OrderProductTM tm : orderProductTMS){
            total += tm.getProductTotal();
        }

        lblOrderTotal.setText(String.valueOf(total));
    }

    // Place Order
    @FXML
    void handlePlaceOrder(ActionEvent event){

        try {

            String customerId = comboCustomerId.getValue();

            ArrayList<OrderProductDTO> orderProductDTOS = new ArrayList<>();

            for(OrderProductTM tm : orderProductTMS){

                orderProductDTOS.add(
                        new OrderProductDTO(
                                tm.getProductId(),
                                tm.getUnitPrice(),
                                tm.getOrderQty()
                        )
                );
            }

            OrderDTO orderDTO =
                    new OrderDTO(customerId,new Date(),orderProductDTOS);

            int result = orderModel.placeOrder(orderDTO);

            if(result > 0){

                new Alert(Alert.AlertType.INFORMATION,
                        "Order Placed Successfully").show();

                orderModel.printInvoice(result);

            }else{
                new Alert(Alert.AlertType.ERROR,
                        "Order Save Failed").show();
            }

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void navigateReset(ActionEvent actionEvent) {
        comboCustomerId.setValue(null);
        lblCustomerNameValue.setText("");
        lblCustomerPhoneValue.setText("");
        lblCustomerEmailValue.setText("");
        comboItemId.setValue(null);
        lblItemNameValue.setText("");
        lblItemQtyValue.setText("");
        lblItemPriceValue.setText("");
        qtyField.setText("");
    }
}