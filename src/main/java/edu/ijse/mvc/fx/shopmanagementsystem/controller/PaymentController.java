package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import java.util.ArrayList;
import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PaymentModel;

public class PaymentController {

    private final PaymentModel paymentModel = new PaymentModel();

    public String savePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentModel.savePayment(paymentDTO);
    }

    public String updatePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentModel.updatePayment(paymentDTO);
    }

    public String deletePayment(String paymentID) throws Exception {
        return paymentModel.deletePayment(paymentID);
    }

    public PaymentDTO searchPayment(String paymentID) throws Exception {
        return paymentModel.searchPayment(paymentID);
    }

    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        return paymentModel.getAllPayments();
    }
    
}
