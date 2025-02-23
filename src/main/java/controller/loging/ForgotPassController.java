package controller.loging;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.custom.ForgetPasswordService;
import util.AppModuler;
import util.LogedDetails;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPassController {
    public JFXButton btnForget;
    public TextField txtUsername;
    public AnchorPane mainAnchor;
    @Inject
    private ForgetPasswordService forgetPasswordService;
    public void btnForgetOnAction(ActionEvent actionEvent) {
        try {
            if(forgetPasswordService.sendOtp(txtUsername.getText())){
                LogedDetails.getInstance().setForgotUsernameText(txtUsername.getText());
                mainAnchor.getChildren().clear();
                Injector injector = Guice.createInjector(AppModuler.getInstance());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/inputOtpPage.fxml"));
                loader.setControllerFactory(injector::getInstance);
                try {
                    mainAnchor.getChildren().add(loader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loginOnClick(ActionEvent actionEvent) {
    }
}
