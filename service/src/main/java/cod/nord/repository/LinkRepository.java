package cod.nord.repository;

import cod.nord.repository.entity.Link;
import cod.nord.repository.entity.Oper;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Nonnull;

@NoRepositoryBean
public interface LinkRepository extends IDao<Link, Integer> {

    Link findByPath(@Nonnull String path);

}
