package cod.nord.repository;

import cod.nord.repository.entity.Path;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PathDao extends DaoOperations<Path, Integer> {

}
