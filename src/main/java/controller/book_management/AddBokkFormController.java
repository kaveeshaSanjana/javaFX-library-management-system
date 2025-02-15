package controller.book_management;

import com.google.inject.Inject;
import dto.Book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.custom.BookService;

import javax.swing.*;
import java.sql.SQLException;

public class AddBokkFormController {
    @FXML
    public AnchorPane anchorPaneAddBook;
    public TextField txtTitle;
    public TextField txtIsbn;
    public TextField txtAuthor;
    public TextField txtStatus;
    public TextField txtGenre;
    public TextField txtRack;
    @Inject
    private BookService bookService;

    public void btnAddBookxOnAction(ActionEvent actionEvent) {
        try {
            if( bookService.addBook(new Book(txtIsbn.getText(), txtTitle.getText(), txtAuthor.getText(), txtGenre.getText(), Integer.parseInt(txtStatus.getText()), Integer.parseInt(txtRack.getText())))){
                JOptionPane.showMessageDialog(null,"Book added Success");
                txtEmpty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void txtEmpty(){
        txtIsbn.setText("");
        txtAuthor.setText("");
        txtTitle.setText("");
        txtGenre.setText("");
        txtStatus.setText("");
        txtRack.setText("");
    }
}
