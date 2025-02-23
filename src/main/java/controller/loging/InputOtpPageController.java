package controller.loging;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import dto.SystemUserChangePassDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.custom.ForgetPasswordService;
import service.custom.SystemUserService;
import service.custom.UserService;
import service.custom.impl.ForgetPasswordServiceImpl;
import util.AppModuler;
import util.LogedDetails;

import java.io.IOException;
import java.sql.SQLException;

public class InputOtpPageController {
    public JFXButton btnChange;
    public TextField txtOtp;
    public TextField txtPasswrod;
    public TextField txtComfermPassword;
    public AnchorPane mainAnchor;
    @Inject
    private SystemUserService systemUserService;
    @Inject
    private ForgetPasswordService forgetPasswordService;
    public void btnChangeOnAction(ActionEvent actionEvent) {
        try {
            if(forgetPasswordService.verifyOtp(LogedDetails.getInstance().getForgotUsernameText(),Integer.parseInt(txtOtp.getText()))){
               systemUserService.changePassword(new SystemUserChangePassDto(LogedDetails.getInstance().getForgotUsernameText(),txtPasswrod.getText(),txtComfermPassword.getText()));
                Stage stage = new Stage();
                Injector injector = Guice.createInjector(AppModuler.getInstance());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logingForm.fxml"));
                loader.setControllerFactory(injector::getInstance);
                stage.setScene(new Scene(loader.load()));
                LogedDetails.getInstance().getPastStage().close();
                LogedDetails.getInstance().setPastStage(stage);
                stage.show();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void signUpOnClick(ActionEvent actionEvent) {
    }
}
