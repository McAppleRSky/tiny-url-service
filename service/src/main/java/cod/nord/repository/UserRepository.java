package cod.nord.repository;

import cod.nord.repository.dao.UserDao;
import cod.nord.repository.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Nullable
    @Override
    public User findById(@Nonnull Integer id) {
        return entityManager.find(User.class, id);
    }

    @Nonnull
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("SELECT * FROM user u").getResultList();
    }

    @Nonnull
    @Override
    public User update(@Nonnull User user) {
        return entityManager.merge(user);
    }

    @Nullable
    @Override
    public User delete(@Nonnull Integer id) {
        User deleted = entityManager.find(User.class, id);
        entityManager.remove(deleted);
        return deleted;
    }

    @Nonnull
    @Override
    public User save(@Nonnull User created) {
        entityManager.persist(created);
        return created;
    }

}
