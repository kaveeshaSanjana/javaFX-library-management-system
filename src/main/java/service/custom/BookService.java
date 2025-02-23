package service.custom;

import dto.Book;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface BookService {
    boolean addBook(Book book) throws SQLException;
    boolean deleteBook(String bookId) throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    Book searchBook(String bookName);
    ObservableList<Book> getAllBooks() throws SQLException;
    ObservableList<Book> getSearchAllBooks(String isbn) throws SQLException;
}
