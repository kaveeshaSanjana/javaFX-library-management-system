package repository;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudRepository<T,ID> {
    boolean save(T t) throws SQLException;
    boolean delete(ID bookId) throws SQLException;
    boolean update(T t) throws SQLException;
    T searchById(ID id) throws SQLException;
    ArrayList<T> getAll() throws SQLException;
}

