package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    public Button btnBurrowAndHandOver1;
    public TextField txtSearch;

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

    public void searchTextChange(KeyEvent inputMethodEvent) {
        if (txtSearch.getText().isEmpty()){loadTable();return;}
        try {
            tblBurrowBook.setItems(bookBurrowService.seachAllById(txtSearch.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
