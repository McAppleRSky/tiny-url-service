package cod.nord.repository;

import cod.nord.repository.entity.Link;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface LinkDao extends IDao<Link, Integer> {
}
