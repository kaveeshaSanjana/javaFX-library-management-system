package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import dto.Book;
import service.custom.BookReturnService;
import util.AppModuler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBodyController {

    public AnchorPane burrowbookAnchorPanBody;
    public TableView tblBooks;
    public TableColumn colBook;
    public TableColumn colUser;
    public TableColumn colBurrowDate;
    public TableColumn colReturnDate;
    public TableColumn colFine;

    @Inject
    private BookReturnService bookReturnService;

    @FXML
private void initialize() {
        colBook.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userNic"));
        colBurrowDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("isbn"));

    loadTable();
}
    public void btnReturnOnAction(ActionEvent actionEvent) throws IOException {
        burrowbookAnchorPanBody.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/burrow_return_management/bookHandOverBody.fxml"));
        loader.setControllerFactory(injector::getInstance);
        burrowbookAnchorPanBody.getChildren().add(loader.load());
    }
    public void loadTable(){
        try {
            tblBooks.setItems(bookReturnService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
