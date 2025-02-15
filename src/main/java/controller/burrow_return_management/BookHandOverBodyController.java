package controller.burrow_return_management;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import dto.Book;
import dto.BurrowBook;
import dto.ReturnBook;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import service.custom.BookBurrowService;
import service.custom.BookReturnService;
import service.custom.BookService;
import service.custom.UserService;
import service.custom.impl.BookBurrowServiceImpl;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookHandOverBodyController {

    @Inject
    private UserService userService;
    @Inject
    private BookService bookService;
    @Inject
    private BookReturnService bookReturnService;
    @Inject
    private BookBurrowService bookBurrowService;

    public JFXComboBox comboUser;
    public JFXComboBox comboBook;

    @FXML
    void initialize(){
        loadUsers();
    }

    public void btnReturnBookOnAcrtion(ActionEvent actionEvent) {
        try {
            if(bookReturnService.add(new ReturnBook(((BurrowBook)comboBook.getSelectionModel().getSelectedItem()).getIsbn(),
                                                    ((User)comboUser.getSelectionModel().getSelectedItem()).getNic(),
                                                      LocalDate.now().toString()))){
                JOptionPane.showMessageDialog(null,"Book Return Success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void loadUsers(){
        try {
            comboUser.setItems(userService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void loadBooks(String userNic){
        try {
            comboBook.setItems(bookBurrowService.getUserBurrowAllBooks(userNic));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmUserOnAction(ActionEvent actionEvent) {
    loadBooks(String.valueOf(((User)comboUser.getSelectionModel().getSelectedItem()).getNic()));
    }

    public void cmbBookSelectedOnAction(ActionEvent actionEvent) {
    }
}
