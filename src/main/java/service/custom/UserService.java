package service.custom;

import dto.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface UserService {
    ObservableList<User> getAll() throws SQLException;
    boolean delete(String userNic) throws SQLException;
    boolean add(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    User get(String userNic) throws SQLException;
}