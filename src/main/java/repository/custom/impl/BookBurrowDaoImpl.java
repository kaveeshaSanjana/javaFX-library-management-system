package repository.custom.impl;

import dbConnection.DBConnection;
import dto.BurrowBook;
import entity.BurrowBookEntity;
import repository.custom.BookBurrowDao;
import util.CrudUtil;
import util.LogedDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BookBurrowDaoImpl implements BookBurrowDao {
    @Override
    public boolean burrowBook(BurrowBook burrowBook) throws SQLException {
        try{
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Boolean b = (Boolean) CrudUtil.execute("INSERT INTO burrow_book VALUES(?,?,?,?,1)", burrowBook.getIsbn(),
                                                                                                    burrowBook.getNic(),
                                                                                                    burrowBook.getDate(),
                                                                                                    LogedDetails.getInstance().getLibraryID());
            if(b){
                DBConnection.getInstance().getConnection().commit();
                return true;
            }else{
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean update(BurrowBook burrowBook,BurrowBook updatedBurrowBook) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Boolean b = (Boolean) CrudUtil.execute("UPDATE burrow_book SET isbn = ?,nic = ?,tel = ? WHERE isbn = ? ,nic = ? ,libraryId = ?)",
                    updatedBurrowBook.getIsbn(),
                    updatedBurrowBook.getNic(),
                    burrowBook.getIsbn(),
                    burrowBook.getNic(),
                    LogedDetails.getInstance().getLibraryID());
            if (b) {
                DBConnection.getInstance().getConnection().commit();
                return true;
            } else {
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }
        }finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(String isbn,String userNic) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Boolean b = (Boolean) CrudUtil.execute("INSERT INTO(`isEnable`) burrow_book VALUES(0) WHERE isbn = ? AND nic = ? AND libraryId= ?",
                    isbn, userNic, LogedDetails.getInstance().getLibraryID());
            if (b){
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
    public ResultSet getByIds(String isbn,String userNic) throws SQLException {
        return (ResultSet) CrudUtil.execute("SELECT id FROM burrow_book WHERE isbn = ? ,nic = ?",
                                            isbn,userNic,LogedDetails.getInstance().getLibraryID());
    }

    @Override
    public ResultSet getByDate(Date date) {
        return null;
    }

    @Override
    public boolean save(BurrowBookEntity burrowBookEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String bookId) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BurrowBookEntity burrowBookEntity) throws SQLException {
        return false;
    }

    @Override
    public BurrowBookEntity searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<BurrowBookEntity> getAll() throws SQLException {
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT * FROM burrow_book WHERE libraryid = ? AND isEnable = 1", LogedDetails.getInstance().getLibraryID());
        ArrayList<BurrowBookEntity> burrowBookEntities = new ArrayList<>();
        while (rst.next()){
            burrowBookEntities.add(new BurrowBookEntity(rst.getString("isbn"),
                                                        rst.getString("nic"),
                                                        rst.getDate("date")));
        }
        return burrowBookEntities;
    }
    @Override
    public ArrayList<BurrowBookEntity> getUserBurrowAllBooks(String userNic) throws SQLException {
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT * FROM burrow_book WHERE libraryid = ? AND isEnable = 1 AND nic = ? ", LogedDetails.getInstance().getLibraryID(),userNic);
        ArrayList<BurrowBookEntity> burrowBookEntities = new ArrayList<>();
        while (rst.next()){
            burrowBookEntities.add(new BurrowBookEntity(rst.getString("isbn"),
                                                        rst.getString("nic"),
                                                        rst.getDate("date")));
        }
        return burrowBookEntities;
    }
}


