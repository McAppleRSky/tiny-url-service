package cod.nord.repository.dao;

import cod.nord.repository.entity.Direction;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DirectionDao extends IDao<Direction, Integer> {
}
