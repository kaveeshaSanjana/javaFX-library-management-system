package repository.custom;

import dto.SystemUser;
import entity.SystemUserChangePassEntity;
import entity.SystemUserEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface SystemUserDao{
    Boolean isCorrect(SystemUserEntity systemUser) throws SQLException;

    Boolean changePassword(SystemUserChangePassEntity map) throws SQLException;
}
