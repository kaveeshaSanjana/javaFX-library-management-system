package service.custom;

import dto.SystemUser;
import dto.SystemUserChangePassDto;
import entity.SystemUserEntity;

import java.sql.SQLException;

public interface SystemUserService {
    Boolean checkUsernamePassword(SystemUser systemUser) throws SQLException;
    boolean changePassword(SystemUserChangePassDto systemUserChangePassDto) throws SQLException;
    SystemUser getUser(String username)throws SQLException;

}
