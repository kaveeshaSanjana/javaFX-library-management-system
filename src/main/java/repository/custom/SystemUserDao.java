package repository.custom;

import entity.SystemUserChangePassEntity;
import entity.SystemUserEntity;

import java.sql.SQLException;

public interface SystemUserDao{
    Boolean isCorrect(SystemUserEntity systemUser) throws SQLException;
    SystemUserEntity findUserByUsername(String username)throws SQLException;
    Boolean changePassword(SystemUserChangePassEntity map) throws SQLException;
}
