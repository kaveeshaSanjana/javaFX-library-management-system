package repository.custom;

import dto.BurrowBook;
import entity.BurrowBookEntity;
import repository.CrudRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface BookBurrowDao extends CrudRepository<BurrowBookEntity,String> {
    boolean burrowBook(BurrowBook burrowBook) throws SQLException;
    boolean update(BurrowBook burrowBook,BurrowBook updatedBurrowBook) throws SQLException;
    boolean delete(String isbn,String userNic) throws SQLException;
    ResultSet getByIds(String isbn,String userNic)throws SQLException;
    ResultSet getByDate(Date date);
    ArrayList<BurrowBookEntity> getUserBurrowAllBooks(String userNic) throws SQLException;
}
