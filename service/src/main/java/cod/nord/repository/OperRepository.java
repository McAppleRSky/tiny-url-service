package cod.nord.repository;

import cod.nord.repository.entity.Oper;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Nonnull;

@NoRepositoryBean
public interface OperRepository extends IDao<Oper, Integer> {

    Oper findByLogin(@Nonnull String login);

}
