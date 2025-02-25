package repository.custom.impl;

import entity.SystemUserChangePassEntity;
import entity.SystemUserEntity;
import repository.custom.SystemUserDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemUserDaoImpl implements SystemUserDao {


    @Override
    public Boolean isCorrect(SystemUserEntity systemUserEntity) throws SQLException {
        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT username,password FROM system_user WHERE username = ? AND password = ?", systemUserEntity.getUsername(), systemUserEntity.getPassword());
        return  resultSet.next();
    }

    @Override
    public SystemUserEntity findUserByUsername(String username) throws SQLException {
         ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM system_user WHERE username = ?",username);
            resultSet.next();
         return new SystemUserEntity(resultSet.getString("username"),"",resultSet.getInt("libraryId"));
    }

    @Override
    public Boolean changePassword(SystemUserChangePassEntity systemUserChangePassEntity) throws SQLException {
        return (Boolean) CrudUtil.execute("UPDATE system_user SET password = ? WHERE username = ?",
                                            systemUserChangePassEntity.getComfermPassword(),
                                            systemUserChangePassEntity.getUsername());
    }
}
