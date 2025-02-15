import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.AppModuler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Starter extends Application{

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(new Date(System.currentTimeMillis()));
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboard/dashboard.fxml"));
        loader.setControllerFactory(injector::getInstance);
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
