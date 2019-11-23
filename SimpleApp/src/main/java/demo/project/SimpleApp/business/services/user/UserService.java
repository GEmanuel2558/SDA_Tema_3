package demo.project.SimpleApp.business.services.user;

import demo.project.SimpleApp.data.entity.UserEntity;
import demo.project.SimpleApp.data.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements IUser {

    @Autowired
    private UserDao userRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<UserEntity> getAllUsersFromDb() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(user -> (UserEntity) user).collect(Collectors.toList());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public UserEntity getAllUsersFromDb(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserEntity insertTheUser(UserEntity theNewUserEntity) {
        return userRepository.save(theNewUserEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public UserEntity updateExistingUser(UserEntity userEntityToUpdate) {
        final Optional<UserEntity> userOptional = userRepository.findById(userEntityToUpdate.getUserId());
        UserEntity persistedUserEntity;
        if (userOptional.isPresent()) {
            persistedUserEntity = userOptional.get();
            persistedUserEntity.setAge(userEntityToUpdate.getAge());
            persistedUserEntity.setEmail(userEntityToUpdate.getEmail());
            persistedUserEntity.setFirstName(userEntityToUpdate.getFirstName());
            persistedUserEntity.setLastName(userEntityToUpdate.getLastName());
        } else {
            persistedUserEntity = userRepository.save(userEntityToUpdate);
        }
        return persistedUserEntity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public boolean deleteExistingUser(UserEntity userEntityToDelete) {
        userRepository.deleteById(userEntityToDelete.getUserId());
        final Optional<UserEntity> doesUserStillExists = userRepository.findById(userEntityToDelete.getUserId());
        return doesUserStillExists.isPresent();
    }

}
