package cod.nord.repository;

import cod.nord.repository.entity.User;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonDao
        extends DaoOperations<User, Integer> {
}
