package service.custom;

import dto.Fine;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface FineService {
    boolean pay(Fine fine) throws SQLException;
    ObservableList<Fine> getAll() throws SQLException;
    ObservableList<Fine> getSearchAll(String isbn) throws SQLException;
}
