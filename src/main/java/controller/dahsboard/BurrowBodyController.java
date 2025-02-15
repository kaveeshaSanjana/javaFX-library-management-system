package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import jakarta.persistence.Id;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.custom.BookBurrowService;
import util.AppModuler;

import java.io.IOException;
import java.sql.SQLException;

public class BurrowBodyController {
    public AnchorPane burrowbookAnchorPanBody;
    public TableView tblBurrowBook;
    public TableColumn colBook;
    public TableColumn colBurrower;
    public TableColumn colBurrowDate;
    public TableColumn colReturnDate;

    @Inject
    private BookBurrowService bookBurrowService;

    @FXML
    void initialize(){
        colBook.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colBurrower.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colBurrowDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReturnDate.setCellValueFactory((new PropertyValueFactory<>("date")));
        loadTable();
    }

    public void btnBurrowMainOnAction(ActionEvent actionEvent) {
    }

    public void btnBurrowOnAction(ActionEvent actionEvent) throws IOException {
        burrowbookAnchorPanBody.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/burrow_return_management/bookBurrowBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        burrowbookAnchorPanBody.getChildren().add(loader.load());
    }

    private void loadTable(){
        try {
            tblBurrowBook.setItems(bookBurrowService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
