package cod.nord.repository;

import cod.nord.repository.entity.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface UserRepository extends IDao<User, Integer> {

    User findByLogin(String login);

}
