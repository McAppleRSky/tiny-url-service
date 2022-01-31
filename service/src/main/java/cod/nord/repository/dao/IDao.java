package cod.nord.repository.dao;

import cod.nord.repository.entity.User;
import org.springframework.data.repository.NoRepositoryBean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@NoRepositoryBean
public interface IDao<T, ID> {

    @Nullable
    T findById(@Nonnull ID id);

    @Nonnull
    List<T> findAll();

    @Nonnull
    T update(@Nonnull T entity);

    @Nullable
    T delete(@Nonnull ID id);

    @Nonnull
    T save(@Nonnull T entity);

}
