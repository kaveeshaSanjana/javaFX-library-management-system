package repository.custom.impl;

import dto.SystemUser;
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
    public Boolean changePassword(SystemUserChangePassEntity systemUserChangePassEntity) throws SQLException {
        return (Boolean) CrudUtil.execute("UPDATE system_user SET password = ? WHERE username = ?",
                                            systemUserChangePassEntity.getComfermPassword(),
                                            systemUserChangePassEntity.getUsername());
    }
}
