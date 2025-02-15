package repository.custom.impl;

import controller.fine_management.LateReturnEntity;
import dbConnection.DBConnection;
import dto.Book;
import dto.User;
import entity.BookEntity;
import entity.ReturnBookEntity;
import entity.UserEntity;
import repository.custom.ReturnBookDao;
import util.CrudUtil;
import util.LogedDetails;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnBookDaoImpl implements ReturnBookDao {
    @Override
    public boolean save(ReturnBookEntity returnBookEntity) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            if(Boolean.TRUE.equals(CrudUtil.execute("INSERT INTO  return_book(isbn,userNic,date,libraryId,isEnable) VALUES(?,?,?,?,1)",returnBookEntity.getIsbn(),
                                                                                returnBookEntity.getUserNic(),
                                                                                returnBookEntity.getDate(),
                                                                                LogedDetails.getInstance().getLibraryID()))){
                DBConnection.getInstance().getConnection().commit();
                return true;
            }else {
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(String bookId) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ReturnBookEntity returnBookEntity) throws SQLException {
        return false;
    }

    @Override
    public ReturnBookEntity searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<ReturnBookEntity> getAll() throws SQLException {
        ArrayList<ReturnBookEntity> returnBookEntities = new ArrayList<>();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT b.title , u.name ,  r.date FROM return_book r LEFT JOIN book b ON b.isbn = r.isbn LEFT JOIN user u ON u.nic = r.usernic WHERE r.libraryId = ? AND isEnable = 1", LogedDetails.getInstance().getLibraryID());
        while (rst.next()){
            returnBookEntities.add(new ReturnBookEntity(rst.getString("title"),rst.getString("name"),rst.getString("date")));
        }
        return returnBookEntities;
    }

    @Override
    public ArrayList<ReturnBookEntity> getUserLateReturns(String nic) throws SQLException {
//
        {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            CrudUtil.execute("SELECT b.date , u.name ,book.title,r.date AS returnDate,r.returnId FROM return_book r INNER JOIN burrow_book b ON r.isbn = b.isbn AND r.usernic = b.nic INNER JOIN user u ON u.nic = b.nic INNER JOIN book ON b.isbn = book.isbn");
        }
        return null;
    }

    @Override
    public Integer getRIdByIds(String nic, String isbn) throws SQLException {
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT r.returnId FROM return_book r INNER JOIN burrow_book b ON r.userNic = b.nic AND r.isbn = b.isbn  WHERE  b.nic = ? AND b.isbn = ? AND r.libraryId = ?", nic, isbn, LogedDetails.getInstance().getLibraryID());
        rst.next();
        return rst.getInt("returnId");
    }

    @Override
    public ArrayList<UserEntity> getLateAllUsers() throws SQLException {
        ArrayList<UserEntity> returnBookEntities = new ArrayList<>();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT b.date ,u.nic, u.name ,book.isbn,book.title,r.date AS returnDate,r.returnId ,r.libraryId FROM return_book r INNER JOIN burrow_book b ON r.isbn = b.isbn AND r.usernic = b.nic INNER JOIN user u ON u.nic = b.nic INNER JOIN book ON b.isbn = book.isbn WHERE r.libraryid=?", LogedDetails.getInstance().getLibraryID());
        while (rst.next()) {
            returnBookEntities.add(new UserEntity(rst.getString("name"),rst.getString("nic"),"",rst.getDate("date")));
        }
        return returnBookEntities;
    }

    @Override
    public ArrayList<BookEntity> getLateUserAllBooks(String userNic) throws SQLException {
        ArrayList<BookEntity> returnBookEntities = new ArrayList<>();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT b.date ,u.nic, u.name ,book.isbn,book.title,r.date AS returnDate,r.returnId ,r.libraryId FROM return_book r INNER JOIN burrow_book b ON r.isbn = b.isbn AND r.usernic = b.nic INNER JOIN user u ON u.nic = b.nic INNER JOIN book ON b.isbn = book.isbn WHERE r.libraryid=? AND u.nic = ?", LogedDetails.getInstance().getLibraryID(),userNic);
        while (rst.next()) {
            returnBookEntities.add(new BookEntity(rst.getString("isbn"),rst.getString("title"),rst.getString("returnDate"),"",1,1));
        }
        return returnBookEntities;
    }
}
