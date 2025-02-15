package controller.burrow_return_management;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import dto.Book;
import dto.BurrowBook;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.shape.Rectangle;
import service.custom.BookBurrowService;
import service.custom.BookService;
import service.custom.UserService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class BookBurrowBodyController {
    public Rectangle anchorPaneAddBook;
    public Label txtIsbn;
    public Label txtAuthor;
    public Label txtRackNo;
    public Label txtNic;
    public Label txtTel;
    public Label txtName;
    public JFXComboBox comboUser;
    public Button btnBorrowed;
    public JFXComboBox comboBook;

    @Inject
    private BookBurrowService bookBurrowService;
    @Inject
    private BookService bookService;
    @Inject
    private UserService userService;

    @FXML
    private void initialize(){
    loadBookIds();
    loadUserNames();
    }

    void loadBookIds(){
        try {
            comboBook.setItems(bookService.getAllBooks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbBookOnAction(ActionEvent actionEvent) {
        setBookDetails(((Book)comboBook.getSelectionModel().getSelectedItem()));
    }

    public void cmbUserOnAction(ActionEvent actionEvent) {
        setUserDetails(((User)comboUser.getSelectionModel().getSelectedItem()));
    }

    void loadUserNames(){
        try {
            comboUser.setItems(userService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void setBookDetails(Book book){
        txtIsbn.setText(book.getISBN());
        txtAuthor.setText(book.getAuthor());
        txtRackNo.setText(book.getRackNumber()+"");
    }

    void setUserDetails(User user){
        txtName.setText(user.getName());
        txtTel.setText(user.getTel());
        txtNic.setText(user.getNic());
    }

    public void btnBurrowOnAction(ActionEvent actionEvent) {
        try {
            if(bookBurrowService.burrowBook(new BurrowBook(txtIsbn.getText(),txtNic.getText(),new Date()))){
                JOptionPane.showMessageDialog(null,"Book Burrowed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
