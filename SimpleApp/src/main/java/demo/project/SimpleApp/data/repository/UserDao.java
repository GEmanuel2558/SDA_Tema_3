package demo.project.SimpleApp.data.repository;

import demo.project.SimpleApp.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Repository;

@Repository
@Indexed
public interface UserDao extends CrudRepository<UserEntity, Long> {
}
