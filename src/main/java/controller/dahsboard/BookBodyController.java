package controller.dahsboard;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.custom.BookService;
import util.AppModuler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class BookBodyController {
    public AnchorPane bookAnchorPanBody;
    public TextField searchField;
    public TableView tblBooks;
    public TableColumn<String,String> colISBN;
    public TableColumn<String,String> colTitle;
    public TableColumn<String,String> colStock;
    public TableColumn<String,String> colRackNo;
    public TableColumn<String,String> colAuthor;

    @Inject
    private BookService bookService;


    @FXML
    public void initialize() {
        colISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colRackNo.setCellValueFactory(new PropertyValueFactory<>("rackNumber"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        loadTable();
    }

    public void btnAddBookOnAction() throws IOException {
        bookAnchorPanBody.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/book-management/addBookForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        bookAnchorPanBody.getChildren().add(loader.load());
    }

    public void btnUpdateOnAction() throws IOException {
        bookAnchorPanBody.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/book-management/updateAndDeletePage.fxml"));
        loader.setControllerFactory(injector::getInstance);
        bookAnchorPanBody.getChildren().add(loader.load());
    }


    public void btnHandOverOnAction(ActionEvent actionEvent) {

    }

    private void loadTable(){
        try {
            tblBooks.setItems(bookService.getAllBooks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
