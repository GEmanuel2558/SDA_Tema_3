package demo.project.SimpleApp.business.services.user;

import demo.project.SimpleApp.data.entity.UserEntity;

import java.util.List;

public interface IUser {

    List<UserEntity> getAllUsersFromDb();

    UserEntity getAllUsersFromDb(Long Id);

    UserEntity insertTheUser(UserEntity theNewUserEntity);

    UserEntity updateExistingUser(UserEntity userEntityToUpdate);

    boolean deleteExistingUser(UserEntity userEntityToDelete);

}
