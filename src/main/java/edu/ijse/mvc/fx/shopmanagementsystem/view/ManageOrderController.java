package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.controller.CustomerController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.ProductController;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.PromotionController;
import edu.ijse.mvc.fx.shopmanagementsystem.dto.*;
import edu.ijse.mvc.fx.shopmanagementsystem.model.OrderModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageOrderController {

    final private CustomerController customerController = new CustomerController();
    final private ProductController productController = new ProductController();
    final private PromotionController promotionController = new PromotionController();

    @FXML
    private TextField productNameTxt;

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
    private TableColumn<OrderProductTM, Void> colAction;

    @FXML
    private TableColumn<OrderProductTM, Double> colDiscount;

    @FXML
    private ComboBox<String> customerIdCombo;

    @FXML
    private ComboBox<String> promotionIdCombo;

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

    final private ObservableList <OrderProductTM> list = FXCollections.observableArrayList();
    final private OrderModel orderModel = new OrderModel();

    @FXML
    void initialize() throws Exception {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellFactory(cell -> new TableCell<OrderProductTM, Void>() {

            Button btn = new Button("Remove");

            {
                btn.getStyleClass().add("remove-btn");
                btn.setOnAction(event -> {

                    OrderProductTM productTM = getTableView().getItems().get(getIndex());
                    list.remove(productTM);
                    loadTable();

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item,empty);
                setGraphic(empty?null:btn);
            }

        });

        loadCustomerIdThread();
        loadProductIdThread();
        loadPromotionIdThread();
        loadTable();
        loadSelectedRow();
        setProductSelectionListner();
        setDiscountSelectionListner();

        orderItemsTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                loadSelectedRow();
            }
        });


    }

    private void loadCustomerIdThread() throws Exception{

            Task<ObservableList<String>> task = new Task<>() {
                @Override
                protected ObservableList<String> call() throws Exception {
                    ArrayList<CustomerDTO> customers = customerController.getAllCustomers();
                    return FXCollections.observableArrayList(
                            customers.stream().map(CustomerDTO::getCustomerId).toList()
                    );
                }
            };

            task.setOnSucceeded(e -> customerIdCombo.setItems(task.getValue()));
            task.setOnFailed(e -> new Alert(Alert.AlertType.ERROR, task.getException().getMessage()).show());

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

    private void loadPromotionIdThread() throws Exception {

        Task <ObservableList<String>> task = new Task(){

            ArrayList <PromotionDTO> promotions = promotionController.getAllPromotions();
            @Override
            protected Object call() throws Exception {
                return FXCollections.observableArrayList(promotions.stream().map(PromotionDTO::getPromotionID).toList());
            }
        };
        task.setOnSucceeded(event -> promotionIdCombo.setItems(task.getValue()));
        task.setOnFailed(event -> new Alert(Alert.AlertType.ERROR, task.getMessage()).show());
        new Thread(task).start();
    }

    private void setProductSelectionListner(){

        productIdCombo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null){
                        try {
                            ProductDTO productDTO = productController.searchProduct(newValue);
                            unitPriceTxt.setText(String.valueOf(productDTO.getUnitPrice()));
                            productNameTxt.setText(productDTO.getName());
                        } catch (Exception e) {
                           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                        }
                    }
                }
        );
    }

    private void setDiscountSelectionListner(){

        promotionIdCombo.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue != null){
                        try {
                            PromotionDTO promotionDTO = promotionController.searchPromotion(newValue);
                            discountTxt.setText(String.valueOf(promotionDTO.getValue()));
                            loadTotalCalc();
                        } catch (Exception e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                        }
                    }
                }
        );

    }

    private void loadTable(){
        orderItemsTable.setItems(list);
        loadTotalCalc();
    }

    private void loadTotalCalc(){

        double total = 0.0;
        double discount = 0.0;

        if (!discountTxt.getText().isEmpty()){
            discount = Double.parseDouble(discountTxt.getText());
        }

        for (OrderProductTM orderProductTM : list){
            total += orderProductTM.getTotal();
        }

        totalAmountTxt.setText(String.valueOf(total));
        double netTotal = total-discount;
        netAmountTxt.setText(String.valueOf(netTotal));

    }

    private void loadSelectedRow(){

        OrderProductTM orderProductTM = orderItemsTable.getSelectionModel().getSelectedItem();

        if(orderProductTM != null){
            productIdCombo.setValue(orderProductTM.getProductID());
            quantityTxt.setText(String.valueOf(orderProductTM.getQty()));
            unitPriceTxt.setText(String.valueOf(orderProductTM.getUnitPrice()));
        }
    }

    @FXML
    void addItemToOrder(ActionEvent event) {
        String selectedId = productIdCombo.getSelectionModel().getSelectedItem();
        if(selectedId == null){
            new Alert(Alert.AlertType.ERROR, "Please Select Product ID ").show();
            return;
        }

        ProductDTO productDTO;

        try {
            productDTO = productController.searchProduct(selectedId);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Product not found!").show();
            return;
        }

        int qty;
        double price;

        try {
            qty = Integer.parseInt(quantityTxt.getText());
            price = Double.parseDouble(unitPriceTxt.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid quantity or unit price").show();
            return;
        }

        double total = qty * price;
        OrderProductTM orderProductTM = new OrderProductTM(
                productDTO.getProductID(),
                productDTO.getName(),
                qty,
                price,
                total
        );

        list.add(orderProductTM);
        loadTable();
    }

    @FXML
    void resetForm(ActionEvent event) {
        customerIdCombo.setValue(null);
        orderDatePicker.setValue(null);
        totalAmountTxt.setText("");
        promotionIdCombo.setValue(null);
        discountTxt.setText("");
        netAmountTxt.setText("");
        productIdCombo.setValue(null);
        quantityTxt.setText("");
        unitPriceTxt.setText("");
    }

    @FXML
    void saveOrder(ActionEvent event) {
        try {
            String customerId = customerIdCombo.getSelectionModel().getSelectedItem();
            LocalDate date = orderDatePicker.getValue();

            List <OrderProductDTO> productDTOS = new ArrayList<>();

            for (OrderProductTM orderProductTM : list){
                OrderProductDTO orderProductDTO = new OrderProductDTO(
                        orderProductTM.getProductID(),
                        orderProductTM.getQty(),
                        orderProductTM.getUnitPrice());
                productDTOS.add(orderProductDTO);
            }

            OrderDTO orderDTO = new OrderDTO(0,customerId, date, productDTOS);
            int result = orderModel.placeOrder(orderDTO);

            if (result > 0) {
                new Alert(Alert.AlertType.INFORMATION, "Order Saved Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Saved Failed").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
