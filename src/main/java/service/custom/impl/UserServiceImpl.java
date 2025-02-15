package service.custom.impl;

import com.google.inject.Inject;
import dto.User;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.UserDao;
import service.custom.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserDao userDao) {
        modelMapper = new ModelMapper();
        this.userDao = userDao;
    }

    public ObservableList<User> getAll() throws SQLException {
        ObservableList<User> all = FXCollections.observableArrayList();
        userDao.getAll().forEach(userEntity -> {
            all.add(modelMapper.map(userEntity, User.class));
        });
        return all;
    }

    @Override
    public boolean delete(String userNic) throws SQLException {
        return userDao.delete(userNic);
    }

    @Override
    public boolean add(User user) throws SQLException {
        return userDao.save(modelMapper.map(user,UserEntity.class));
    }

    public boolean addUser(User user) throws SQLException {
        return userDao.save(modelMapper.map(user, UserEntity.class));
    }

    public boolean updateUser(User user) throws SQLException {
        return userDao.update(modelMapper.map(user, UserEntity.class));
    }
    public User get(String userNic) throws SQLException {
        return modelMapper.map(userDao.searchById(userNic),User.class);
    }
}
