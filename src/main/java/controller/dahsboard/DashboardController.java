package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import util.AppModuler;
import util.LogedDetails;

import java.io.IOException;

public class DashboardController {
    @FXML
    public BorderPane boarderPainMain;
    public Label txtUsername;

    @FXML
    public void initialize() {
        boarderPainMain.getChildren().clear();
        try {
            txtUsername.setText(LogedDetails.getInstance().getUserName());
            boarderPainMain.getChildren().add(FXMLLoader.load(getClass().getResource("/view/dashboard/homeBody.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void lblBooksClicked() throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/homeBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());
    }

    public void lblBookClickedAction(MouseEvent mouseEvent) throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/bookBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());
    }

    public void userClickedOnAction(MouseEvent mouseEvent) throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/userBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());    }

    public void burrowClickedOnAction(MouseEvent mouseEvent) throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/burrowBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());
    }

    public void returnClickedOnAction(MouseEvent mouseEvent) throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/returnBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());
    }

    public void lblFineClickedAction(MouseEvent mouseEvent) throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/fineBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());
    }

    public void reportsClickOnAction(MouseEvent mouseEvent) throws IOException {
        boarderPainMain.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/report/reportBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        boarderPainMain.getChildren().add(loader.load());
    }
}
