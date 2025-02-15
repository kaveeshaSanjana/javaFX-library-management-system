package service.custom.impl;

import com.google.inject.Inject;
import dto.BurrowBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.BookBurrowDao;
import service.custom.BookBurrowService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookBurrowServiceImpl implements BookBurrowService {
    @Inject
    private BookBurrowDao bookBurrowDao;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean burrowBook(BurrowBook burrowBook) throws SQLException {
        return bookBurrowDao.burrowBook(burrowBook);
    }

    @Override
    public boolean updateBurrow(BurrowBook burrowBook,BurrowBook updatedBurrowBook) throws SQLException {
        updatedBurrowBook.setDate(new Date(System.currentTimeMillis()));
        return bookBurrowDao.update(burrowBook,updatedBurrowBook);
    }

    @Override
    public boolean deleteBurrow(String isbn ,String userNic) throws SQLException {
        return bookBurrowDao.delete(isbn,userNic);
    }

    @Override
    public  ArrayList<BurrowBook> getByIds(String isbn,String userNic) throws SQLException {
        ResultSet rst = bookBurrowDao.getByIds(isbn, userNic);
        rst.next();
        return null;
    }

    @Override
    public ArrayList<BurrowBook> getByDate(Date date) throws SQLException {
        ResultSet byDate = bookBurrowDao.getByDate(date);
        byDate.next();
        return null;
    }


    @Override
    public ObservableList<BurrowBook> getAll() throws SQLException {
        ObservableList<BurrowBook> burrowBooks = FXCollections.observableArrayList();
        bookBurrowDao.getAll().forEach(burrowBookEntity -> {
            burrowBooks.add(modelMapper.map(burrowBookEntity,BurrowBook.class));
        });
        return burrowBooks;
    }

    @Override
    public ObservableList<BurrowBook> getUserBurrowAllBooks(String userNic) throws SQLException {
        ObservableList<BurrowBook> userburrowBooks = FXCollections.observableArrayList();
        bookBurrowDao.getUserBurrowAllBooks(userNic).forEach(burrowBookEntity -> {
            userburrowBooks.add(modelMapper.map(burrowBookEntity,BurrowBook.class));
        });
        return userburrowBooks;
    }
}
