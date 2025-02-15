package controller.fine_management;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXComboBox;
import controller.model.FineTrans;
import dto.Book;
import dto.Fine;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.custom.BookReturnService;
import service.custom.FineService;
import service.custom.UserService;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class PayFineFormController {

    public Label lblBurrowDate;
    public Label lblLatedate;
    public TextField txtFine;
    public JFXComboBox comboUser;
    public JFXComboBox comboBook;

    @Inject
    private FineService fineService;
    @Inject
    private UserService userService;
    @Inject
    private BookReturnService bookReturnService;

    @FXML
    void initialize(){
        try {
            comboUser.setItems(bookReturnService.getLateAllUsers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmUserOnAction(ActionEvent actionEvent) {
        try {
            comboBook.setItems(bookReturnService.getLateUserAllBook(((User)comboUser.getSelectionModel().getSelectedItem()).getNic()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbBookSelectedOnAction(ActionEvent actionEvent) {
        setDetails();
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
        try {
            boolean pay = fineService.pay(new Fine(((User) comboUser.getSelectionModel().getSelectedItem()).getNic(), ((Book) comboBook.getSelectionModel().getSelectedItem()).getISBN(), Double.parseDouble(txtFine.getText()), LocalDate.now().toString()));
            if(pay){
                JOptionPane.showMessageDialog(null,"Payment Success");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void setDetails(){
        lblBurrowDate.setText(((User)comboUser.getSelectionModel().getSelectedItem()).getDate().toString());
        lblLatedate.setText(((Book)comboBook.getSelectionModel().getSelectedItem()).getAuthor());
    }
}
