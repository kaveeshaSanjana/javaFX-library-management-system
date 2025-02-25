package service.custom.impl;

import com.google.inject.Inject;
import dto.SystemUser;
import dto.SystemUserChangePassDto;
import entity.SystemUserChangePassEntity;
import entity.SystemUserEntity;
import org.modelmapper.ModelMapper;
import repository.custom.SystemUserDao;
import service.custom.SystemUserService;

import java.sql.SQLException;

public class SystemUserImpl implements SystemUserService {

    @Inject
    private SystemUserDao systemUserDao;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean checkUsernamePassword(SystemUser systemUser) throws SQLException {
        return systemUserDao.isCorrect(modelMapper.map(systemUser,SystemUserEntity.class));
    }
    @Override
    public boolean changePassword(SystemUserChangePassDto systemUserChangePassDto) throws SQLException {
        if (systemUserChangePassDto.getPassword().equals(systemUserChangePassDto.getComfermPassword())){
            return systemUserDao.changePassword(modelMapper.map(systemUserChangePassDto, SystemUserChangePassEntity.class));
        }
        return false;
    }

    @Override
    public SystemUser getUser(String username) throws SQLException {
        return modelMapper.map(systemUserDao.findUserByUsername(username),SystemUser.class);
    }
}
