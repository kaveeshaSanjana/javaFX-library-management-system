package repository.custom.impl;

import dbConnection.DBConnection;
import dto.User;
import entity.BookEntity;
import entity.UserEntity;
import repository.custom.UserDao;
import util.CrudUtil;
import util.LogedDetails;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class UserDaoImpl implements UserDao {

    private String sql = "libraryId = "+LogedDetails.getInstance().getLibraryID()+" AND isEnable = 1 ";

    @Override
    public boolean save(UserEntity user) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            if (addUserToUserTable(user) && addUserToLibraryUser(user.getNic())) {
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
    public boolean delete(String userNic) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            if (Boolean.TRUE.equals(CrudUtil.execute("UPDATE library_user SET isEnable = 0 WHERE usernic = ?;", userNic))) {
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

    public boolean update(UserEntity user) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            Boolean b = (Boolean) CrudUtil.execute("UPDATE user JOIN library_user ON user.nic = library_user.userNic SET user.name = ?, user.telephone = ? ,library_user.registerdate = ? WHERE user.nic = ?", user.getName(), user.getTel(), user.getDate(), user.getNic());
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
    public UserEntity searchById(String nic) throws SQLException {
        UserEntity userEntity = new UserEntity();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT * FROM library_user WHERE usernic = ? AND "+sql,nic);
        rst.next();
            userEntity.setDate(rst.getDate("registerDate"));
        return userEntity;
    }

    @Override
    public ArrayList<UserEntity> getAll() throws SQLException {
        ArrayList<UserEntity> userEntities = new ArrayList<>();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT * FROM user INNER JOIN library_user ON user.nic = library_user.usernic WHERE "+sql);
        while (rst.next()){
            userEntities.add(new UserEntity(rst.getString("name"),
                                            rst.getString("nic"),
                                            rst.getString("telephone"),
                                            rst.getDate("registerDate")));
        }
        return userEntities;
    }

    @Override
    public UserEntity serchByUsername(String name) {
        return null;
    }

    boolean addUserToUserTable(UserEntity userEntity) throws SQLException {
        if(((ResultSet)CrudUtil.execute("SELECT nic FROM user WHERE nic = ?",userEntity.getNic())).next()){
            return true;
        }else {
                return (Boolean) CrudUtil.execute("INSERT INTO user VALUES(?,?,?)", userEntity.getName(),
                        userEntity.getNic(),
                        userEntity.getTel());
        }
    }

    boolean addUserToLibraryUser(String userNic) throws SQLException {
        Date date = new Date();
        System.out.println(date.getYear()+"-"+date.getMonth()+"-"+date.getDay());
        return (Boolean) CrudUtil.execute("INSERT INTO library_user VALUES(?,?,?,?)",  LogedDetails.getInstance().getLibraryID(),userNic,(date.getYear()+"-"+date.getMonth()+"-"+date.getDay()),1);
    }


}
