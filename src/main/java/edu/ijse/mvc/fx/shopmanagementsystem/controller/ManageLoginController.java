package edu.ijse.mvc.fx.shopmanagementsystem.controller;

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

    private final String usernameRegex = "^[A-Za-z0-9]{3,20}$";
    private final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,20}$";

    public void navigateLogin(ActionEvent actionEvent) {
        try {
            String username = userTxt.getText();
            String password = passwordTxt.getText();

            if (!username.matches(usernameRegex)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Username!\nOnly letters and numbers allowed (3-20 chars).").show();
                return;
            }

            if (!password.matches(passwordRegex)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Password!\nPassword must be 6-20 chars, with at least 1 uppercase, 1 lowercase, and 1 number.").show();
                return;
            }

            LoginDTO loginDTO = loginModel.findByUsernameAndPassword(username, password);

            if (loginDTO != null) {

                Stage stage = (Stage) loginBtn.getScene().getWindow();
                Scene scene;

                if (loginDTO.getRole().equalsIgnoreCase("Admin")) {
                    scene = new Scene(
                            FXMLLoader.load(getClass().getResource(
                                    "/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu.fxml"
                            ))
                    );
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    new Alert(Alert.AlertType.INFORMATION, "Admin Login Successfully!").show();

                } else if (loginDTO.getRole().equalsIgnoreCase("Cashier")) {
                    scene = new Scene(
                            FXMLLoader.load(getClass().getResource(
                                    "/edu/ijse/mvc/fx/shopmanagementsystem/MainMenu2.fxml"
                            ))
                    );
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    new Alert(Alert.AlertType.INFORMATION, "Cashier Login Successfully!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Credentials").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Username or Password is incorrect!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void navigateClear(ActionEvent actionEvent) {
        userTxt.setText("");
        passwordTxt.setText("");
    }
}
