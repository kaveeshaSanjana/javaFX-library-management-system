package service.custom;

import controller.fine_management.LateReturnEntity;
import dto.Book;
import dto.ReturnBook;
import dto.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface BookReturnService {
    ObservableList<ReturnBook> getAll() throws SQLException;
    ObservableList<User> getLateAllUsers() throws SQLException;
    ObservableList<Book> getLateUserAllBook(String userNic) throws SQLException;
    boolean add(ReturnBook returnBook) throws SQLException;

}
