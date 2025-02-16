package controller.dahsboard;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;
import service.custom.impl.ReportService;

import java.sql.SQLException;


public class ReportBodyController {
    public TextField txtNic;
    @Inject
    ReportService reportService;

    public void allUserReportGenerateOnAction(ActionEvent actionEvent) {
        try {
            reportService.generateAllUserReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void userReportGnerateOnAction(ActionEvent actionEvent) {
        try {
            reportService.generateUserDetailReport(txtNic.getText());
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
