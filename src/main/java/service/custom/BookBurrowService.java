package service.custom;

import dto.BurrowBook;
import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface BookBurrowService extends SuperService {
    boolean burrowBook(BurrowBook burrowBook) throws SQLException;
    boolean updateBurrow(BurrowBook burrowBook,BurrowBook updatedBurrowBook) throws SQLException;
    boolean deleteBurrow(String isbn,String userNic) throws SQLException;
    ArrayList<BurrowBook>  getByIds(String isbn,String userNic) throws SQLException;
    ArrayList<BurrowBook> getByDate(Date date) throws SQLException;
    ObservableList<BurrowBook> getAll() throws SQLException;
    ObservableList<BurrowBook> getUserBurrowAllBooks(String userNic) throws SQLException;
}
