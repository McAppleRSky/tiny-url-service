package cod.nord.repository;

import cod.nord.repository.entity.Url;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UrlDao extends DaoOperations<Url, Integer> {
}
