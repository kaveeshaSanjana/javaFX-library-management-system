package repository.custom.impl;
import dbConnection.DBConnection;
import entity.BookEntity;
import repository.custom.BookDao;
import util.CrudUtil;
import util.LogedDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean save(BookEntity bookEntity) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT isbn FROM book WHERE isbn = ? ", bookEntity.getIsbn());
            if (!resultSet.next()) {
                addBookT(bookEntity);
            }
            if (addBookLibraryT(bookEntity)) {
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
    public boolean delete(String bookId) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean b =  CrudUtil.execute("DELETE FROM library_book WHERE isbn = ? AND libraryId = ?", bookId,LogedDetails.getInstance().getLibraryID());
            deleteBookFromBookT(bookId);
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
    public boolean update(BookEntity bookEntity) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean b = updateBookLibraryT(bookEntity);
            if(b){
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
    public BookEntity searchById(String isbn) throws SQLException {
            ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM library_book LEFT JOIN book ON library_book.isbn = book.isbn  WHERE book.isbn = ? AND library_book.libraryId = ?", isbn,LogedDetails.getInstance().getLibraryID());
            if(resultSet.next() ){
             return new BookEntity(resultSet.getString("isbn"),
                                    resultSet.getString("title"),
                                    resultSet.getString("author"),
                                    resultSet.getString("genre"),
                                    resultSet.getInt("stock"),
                                    resultSet.getInt("rackNo"));

        }
        return null;
    }

    @Override
    public ArrayList<BookEntity> searchAllById(String isbn) throws SQLException {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();
        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT isbn FROM library_book WHERE libraryId = ? AND isbn LIKE ?", LogedDetails.getInstance().getLibraryID(),"%"+isbn+"%");
        while (resultSet.next()){
            bookEntities.add(searchById(resultSet.getString("isbn")));
        }
        return bookEntities;    }

    @Override
    public ArrayList<BookEntity> getAll() throws SQLException {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();
        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT isbn FROM library_book WHERE libraryId = ?", LogedDetails.getInstance().getLibraryID());
        while (resultSet.next()){
            bookEntities.add(searchById(resultSet.getString("isbn")));
        }
        return bookEntities;
    }

    private boolean deleteBookFromBookT(String isbn) throws SQLException {
        //this method check is there any usages that book in another library .if not that book was deleted from main book table
        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT isbn FROM library_book WHERE isbn = ? AND libraryId != ?", isbn, LogedDetails.getInstance().getLibraryID());
        if (!resultSet.next()) {
            CrudUtil.execute("DELETE FROM book WHERE isbn = ?", isbn);
        }
        return true;
    }

    private boolean updateBookLibraryT(BookEntity bookEntity) throws SQLException {
        return CrudUtil.execute("UPDATE library_book SET stock = ?, rackNo = ? WHERE isbn = ? AND libraryId = ?", bookEntity.getStock(),
                                                                                                                            bookEntity.getRackNumber(),
                                                                                                                            bookEntity.getIsbn(),
                                                                                                                            LogedDetails.getInstance().getLibraryID());
    }

    private boolean addBookT(BookEntity bookEntity) throws SQLException {
         return (Boolean) CrudUtil.execute("INSERT INTO book VALUES (?,?,?,?)", bookEntity.getIsbn(),
                                                                                    bookEntity.getTitle(),
                                                                                    bookEntity.getAuthor(),
                                                                                    bookEntity.getGenre());
    }

    private boolean addBookLibraryT(BookEntity bookEntity) throws SQLException {
        return (Boolean) CrudUtil.execute("INSERT INTO library_book VALUES (?,?,?,?)",LogedDetails.getInstance().getLibraryID() ,
                                                                                        bookEntity.getIsbn(),
                                                                                        bookEntity.getStock(),
                                                                                        bookEntity.getRackNumber());
    }
}
