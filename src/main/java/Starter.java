import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.AppModuler;
import util.LogedDetails;

public class Starter extends Application{

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {

        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logingForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        stage.setScene(new Scene(loader.load()));
        LogedDetails.getInstance().setPastStage(stage);
        stage.show();
    }
}
