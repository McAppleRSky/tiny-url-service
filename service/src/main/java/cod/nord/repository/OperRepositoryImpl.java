package cod.nord.repository;

import cod.nord.repository.entity.Oper;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OperRepositoryImpl implements OperRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Nullable
    @Override
    public Oper findById(@Nonnull Integer id) {
        return entityManager.find(Oper.class, id);
    }

    @Nonnull
    @Override
    public List<Oper> findAll() {
        return entityManager.createQuery("SELECT o FROM Oper o").getResultList();
    }

    @Nonnull
    @Override
    public Oper update(@Nonnull Oper user) {
        return entityManager.merge(user);
    }

    @Nullable
    @Override
    public Oper delete(@Nonnull Integer id) {
        Oper deleted = entityManager.find(Oper.class, id);
        entityManager.remove(deleted);
        return deleted;
    }

    @Nonnull
    @Override
    public Oper save(@Nonnull Oper created) {
        entityManager.persist(created);
        return created;
    }

    @Nullable
    @Override
    public Oper findByLogin(@Nonnull String login) {
        TypedQuery<Oper> query = entityManager.createQuery( //
                "SELECT o FROM Oper o WHERE o.login=:login",
                Oper.class );
        return query.setParameter("login", login).getSingleResult();
    }

}
