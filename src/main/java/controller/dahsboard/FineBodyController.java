package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.AppModuler;

import java.io.IOException;

public class FineBodyController
{
    public AnchorPane finebookAnchorPanBody;

    public void btnFineOnAction(ActionEvent actionEvent) throws IOException {
        finebookAnchorPanBody.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fine-management/payFineForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        finebookAnchorPanBody.getChildren().add(loader.load());

    }
}
