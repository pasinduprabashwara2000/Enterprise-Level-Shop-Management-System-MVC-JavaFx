package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.LoginDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ManageLoginController {

    private final LoginModel loginModel = new LoginModel();

    @FXML
    private Button clearBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField userTxt;

    public void navigateLogin(ActionEvent actionEvent) {

        try {
            LoginDTO loginDTO = loginModel.findByUsernameAndPassword(
                userTxt.getText(),
                passwordTxt.getText()
            );

            if(loginDTO != null){
                if(loginDTO.getRole().equalsIgnoreCase("Admin")){
                    ((Stage) loginBtn.getScene().getWindow())
                            .setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu.fxml"))));
                    new Alert(Alert.AlertType.INFORMATION," Admin Login Successfully !").show();
                } else if (loginDTO.getRole().equalsIgnoreCase("Cashier")){
                    ((Stage) loginBtn.getScene().getWindow())
                            .setScene(new Scene(FXMLLoader.load(getClass().getResource("/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu2.fxml"))));
                    new Alert(Alert.AlertType.ERROR,"Cashier Login Successfully").show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Invalid Credentials").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void navigateClear(ActionEvent actionEvent) {
        userTxt.setText("");
        passwordTxt.setText("");
    }
}
