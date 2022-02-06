package cod.nord.repository;

import cod.nord.repository.entity.Link;
import cod.nord.repository.entity.Oper;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Nullable
    @Override
    public Link findById(@Nonnull Integer integer) {
        throw new NotImplementedException("");
//        return null;
    }

    @Nonnull
    @Override
    public List<Link> findAll() {
        return entityManager.createQuery("SELECT l FROM Link l").getResultList();
    }

    @Nonnull
    @Override
    public Link update(@Nonnull Link entity) {
        return entityManager.merge(entity);
    }

    @Nullable
    @Override
    public Link delete(@Nonnull Integer id) {
        Link deleted = entityManager.find(Link.class, id);
        entityManager.remove(deleted);
        return deleted;
    }

    @Nonnull
    @Override
    public Link save(@Nonnull Link created) {
        entityManager.persist(created);
        return created;
    }

    public Link findByPath(@Nonnull String path) {
        TypedQuery<Link> query = entityManager.createQuery( //
                "SELECT l FROM Link l WHERE l.path=:path",
                Link.class );
        return query
                .setParameter("path", path)
                .getSingleResult();
    }

}
