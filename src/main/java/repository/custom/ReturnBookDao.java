package repository.custom;

import controller.fine_management.LateReturnEntity;
import dto.ReturnBook;
import entity.BookEntity;
import entity.ReturnBookEntity;
import entity.UserEntity;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReturnBookDao extends CrudRepository<ReturnBookEntity,String> {
 ArrayList<ReturnBookEntity> getUserLateReturns(String nic) throws SQLException;
 Integer getRIdByIds(String nic,String isbn) throws SQLException;
 ArrayList<UserEntity> getLateAllUsers() throws SQLException;
 ArrayList<BookEntity> getLateUserAllBooks(String userNic) throws SQLException;
 ReturnBookEntity searchById(int s) throws SQLException;
}
