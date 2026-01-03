package edu.ijse.mvc.fx.shopmanagementsystem.controller;

import edu.ijse.mvc.fx.shopmanagementsystem.DTO.PaymentDTO;
import edu.ijse.mvc.fx.shopmanagementsystem.model.PaymentModel;
import java.util.ArrayList;

public class PaymentController {

    final private PaymentModel paymentModel = new PaymentModel();

    public String savePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentModel.savePayment(paymentDTO);
    }

    public String updatePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentModel.updatePayment(paymentDTO);
    }

    public String deletePayment(String paymentId) throws Exception {
        return paymentModel.deletePayment(paymentId);
    }

    public PaymentDTO searchPayment(String paymentId) throws Exception {
        return paymentModel.searchPayment(paymentId);
    }

    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        return paymentModel.getAllPayments();
    }

}
