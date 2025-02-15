package repository.custom;

import dto.User;
import entity.UserEntity;
import repository.CrudRepository;

public interface UserDao extends CrudRepository<UserEntity,String> {
    UserEntity serchByUsername(String name);
}
