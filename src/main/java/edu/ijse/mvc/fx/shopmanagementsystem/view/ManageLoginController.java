package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.LoginDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ManageLoginController {

    private final LoginController loginController = new LoginController();

    @FXML
    private Button clearBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private ComboBox<String> roleCombo;

    @FXML
    private TextField userTxt;

    public void navigateLogin(ActionEvent actionEvent) {

        try {
            LoginDTO loginDTO = new LoginDTO(
                    userTxt.getText(),
                    passwordTxt.getText(),
                    roleCombo.getValue()
            );
            LoginDTO rsp = loginController.login(loginDTO);

            if(rsp != null){
                if(rsp.getRole().equalsIgnoreCase("Admin")){
                    new Alert(Alert.AlertType.INFORMATION,"Login Successfully "+rsp.getUserName()).show();
                    ((Stage) loginBtn.getScene().getWindow())
                            .setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu.fxml"))));
                } else if (rsp.getRole().equalsIgnoreCase("Cashier")){
                    new Alert(Alert.AlertType.INFORMATION,"Login Successfully "+rsp.getUserName()).show();
                    ((Stage) loginBtn.getScene().getWindow())
                            .setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu2.fxml"))));
                } else {
                    new Alert(Alert.AlertType.ERROR,"Error").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR,"Invalid Credentials").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    public void navigateClear(ActionEvent actionEvent) {
        userTxt.setText("");
        passwordTxt.setText("");
        roleCombo.setValue(null);
    }
}
