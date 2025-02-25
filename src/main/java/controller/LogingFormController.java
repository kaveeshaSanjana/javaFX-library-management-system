package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import dto.SystemUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.custom.SystemUserService;
import util.AppModuler;
import util.LogedDetails;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LogingFormController {
    public AnchorPane mainAnchor;
    @Inject
    private SystemUserService systemUserService;

    public JFXButton btnLogin;
    public TextField txtUsername;
    public TextField txtPassword;

    public void signUpOnClick(ActionEvent actionEvent) {
    }

    public void forgotPasswordOnClick(ActionEvent actionEvent) {
        mainAnchor.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/forgetPasword.fxml"));
        loader.setControllerFactory(injector::getInstance);
        try {
            mainAnchor.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();

        try {
            if (Boolean.TRUE.equals(systemUserService.checkUsernamePassword(new SystemUser(txtUsername.getText(),txtPassword.getText(),0)))){

                SystemUser user = systemUserService.getUser(txtUsername.getText());
                LogedDetails.getInstance().setUserName(user.getUsername());
                LogedDetails.getInstance().setLibraryID(user.getLibraryId());

                LogedDetails.getInstance().getPastStage().close();
                Injector injector = Guice.createInjector(AppModuler.getInstance());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/dashboard.fxml"));
                loader.setControllerFactory(injector::getInstance);
                try {
                    stage.setScene(new Scene(loader.load()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }else {
                JOptionPane.showMessageDialog(null,"Username or Password is incorrect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
