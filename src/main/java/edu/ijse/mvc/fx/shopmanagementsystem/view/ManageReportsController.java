package edu.ijse.mvc.fx.shopmanagementsystem.view;

import edu.ijse.mvc.fx.shopmanagementsystem.DB.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ManageReportsController {

    @FXML
    private Button btnGenerate;

    @FXML
    private ComboBox<String> cmbReportType;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    public void initialize() {
        fromDate.setValue(LocalDate.now().minusDays(7));
        toDate.setValue(LocalDate.now());
    }

    @FXML
    void generateReports() {

        String reportType = cmbReportType.getValue();
        LocalDate from = fromDate.getValue();
        LocalDate to = toDate.getValue();

        if (reportType == null || from == null || to == null) {
            showError("Please fill in all fields");
            return;
        }

        if (from.isAfter(to)) {
            showError("From date cannot be after To date");
            return;
        }

        try {
            String reportFile = getReportFile(reportType);

            if (reportFile == null) {
                showError("Invalid report type selected");
                return;
            }

            InputStream reportStream =
                    getClass().getResourceAsStream("/edu/ijse/mvc/fx/shopmanagementsystem/reports/" + reportFile);

            if (reportStream == null) {
                showError("Report file not found: " + reportFile);
                return;
            }

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(reportStream);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("FROM_DATE", Date.valueOf(from));
            parameters.put("TO_DATE", Date.valueOf(to));

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            parameters,
                            DBConnection.getInstance().getConnection()
                    );

            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to generate report");
        }
    }

    private String getReportFile(String reportType) {
        switch (reportType) {
            case "Payment Report":
                return "payment.jrxml";
            case "Return Report" :
                return "returns.jrxml";
            default:
                return null;
        }
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }
}
