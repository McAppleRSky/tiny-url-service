package cod.nord.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PathDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

}
