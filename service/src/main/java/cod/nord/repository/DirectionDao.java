package cod.nord.repository;

import cod.nord.repository.entity.Direction;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface DirectionDao extends IDao<Direction, Integer> {
}
