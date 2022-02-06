package cod.nord.repository;

import cod.nord.repository.entity.Link;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface LinkRepository extends IDao<Link, Integer> {
}
