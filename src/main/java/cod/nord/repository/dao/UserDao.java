package cod.nord.repository.dao;

import cod.nord.repository.entity.User;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserDao extends IDao<User, Integer> {
}
