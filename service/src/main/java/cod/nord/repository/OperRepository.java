package cod.nord.repository;

import cod.nord.repository.entity.Oper;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface OperRepository extends IDao<Oper, Integer> {

    Oper findByLogin(String login);

}
