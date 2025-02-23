package service.custom;

import dto.SystemUser;
import dto.SystemUserChangePassDto;

import java.sql.SQLException;

public interface SystemUserService {
    Boolean checkUsernamePassword(SystemUser systemUser) throws SQLException;
    boolean changePassword(SystemUserChangePassDto systemUserChangePassDto) throws SQLException;

}
