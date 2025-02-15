package controller.book_management;

import com.google.inject.Inject;
import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.custom.BookService;

import javax.swing.*;
import java.sql.SQLException;

public class UdateAndDeletePageController {

    public AnchorPane anchorPaneAddBook;
    public ComboBox comboBookTitle;
    public TextField txtAuthor;
    public TextField txtGenre;
    public TextField txtRackNo;
    public TextField txtTitle;
    public TextField txtStock;
    public TextField txtISBN;

    @Inject
    BookService bookService;

    @FXML
    public void initialize(){
        setBookTitle();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if( bookService.updateBook(new Book(txtISBN.getText(), txtTitle.getText(), txtAuthor.getText(), txtGenre.getText(), Integer.parseInt(txtStock.getText()), Integer.parseInt(txtRackNo.getText())))){
                JOptionPane.showMessageDialog(null,"Updated Success");
                txtEmpty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if(bookService.deleteBook(txtISBN.getText())){
                JOptionPane.showMessageDialog(null,"Deleted Success");
                txtEmpty();
            }else {
                JOptionPane.showMessageDialog(null,"Deleted Failed");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Deleted Failed");
        }
    }

    private void txtEmpty(){
        txtISBN.setText("");
        txtAuthor.setText("");
        txtTitle.setText("");
        txtGenre.setText("");
        txtStock.setText("");
        txtRackNo.setText("");
        txtISBN.setEditable(true);
    }
    private void setDetails(Book book){
        txtISBN.setText(book.getISBN());
        txtAuthor.setText(book.getAuthor());
        txtTitle.setText(book.getTitle());
        txtGenre.setText(book.getGenre());
        txtStock.setText(book.getStock()+"");
        txtRackNo.setText(book.getRackNumber()+"");
    }

    private void setBookTitle(){
        try {
            comboBookTitle.setItems(bookService.getAllBooks());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbBookOnAction(ActionEvent actionEvent) {
        setDetails(((Book)comboBookTitle.getSelectionModel().getSelectedItem()));
        txtISBN.setEditable(false);
    }
}
