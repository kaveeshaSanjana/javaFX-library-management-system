package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.impl.BookDaoImpl;
import service.custom.BookService;

import java.sql.SQLException;

public class BookServiceImpl implements BookService {

    private final BookDaoImpl bookDao = new BookDaoImpl();
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addBook(Book book) throws SQLException {
      return bookDao.save(modelMapper.map(book, BookEntity.class));
    }

    @Override
    public boolean deleteBook(String bookId) throws SQLException {
        return bookDao.delete(bookId);
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        return bookDao.update(modelMapper.map(book,BookEntity.class));
    }

    @Override
    public Book searchBook(String bookName) {
        return null;
    }

    @Override
    public ObservableList<Book> getAllBooks() throws SQLException {
        ObservableList<Book> books = FXCollections.observableArrayList();
        bookDao.getAll().forEach(bookEntity -> {
            books.add(modelMapper.map(bookEntity,Book.class));
        });

        return books;
    }
}
