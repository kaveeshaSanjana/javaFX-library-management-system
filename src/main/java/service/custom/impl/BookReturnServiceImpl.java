package service.custom.impl;

import com.google.inject.Inject;
import controller.fine_management.LateReturnEntity;
import dto.Book;
import dto.ReturnBook;
import dto.User;
import entity.ReturnBookEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.ReturnBookDao;
import service.custom.BookReturnService;

import java.sql.SQLException;

public class BookReturnServiceImpl implements BookReturnService {
@Inject
private ReturnBookDao returnBookDao;
ModelMapper modelMapper = new ModelMapper();

    @Override
    public ObservableList<ReturnBook> getAll() throws SQLException {
        ObservableList<ReturnBook> all = FXCollections.observableArrayList();
        returnBookDao.getAll().forEach(returnBookEntity -> {
            all.add(modelMapper.map(returnBookEntity,ReturnBook.class));
    });
        return all;
    }

    @Override
    public ObservableList<ReturnBook> getSearchAll(String title) throws SQLException {
        ObservableList<ReturnBook> all = FXCollections.observableArrayList();
        returnBookDao.searchAllById(title).forEach(returnBookEntity -> {
            all.add(modelMapper.map(returnBookEntity,ReturnBook.class));
        });
        return all;

    }

    @Override
    public ObservableList<User> getLateAllUsers() throws SQLException {
        ObservableList<User> all = FXCollections.observableArrayList();
        returnBookDao.getLateAllUsers().forEach(userEntity -> {
            all.add(modelMapper.map(userEntity, User.class));
        });
        return all;
    }

    @Override
    public ObservableList<Book> getLateUserAllBook(String userNic) throws SQLException {
        ObservableList<Book> all = FXCollections.observableArrayList();
        returnBookDao.getLateUserAllBooks(userNic).forEach(bookEntity -> {
            all.add(modelMapper.map(bookEntity, Book.class));
        });
        return all;
    }

    @Override
    public boolean add(ReturnBook returnBook) throws SQLException {
        return returnBookDao.save(modelMapper.map(returnBook, ReturnBookEntity.class));
    }
}
