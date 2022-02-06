package cod.nord.service.link;

import cod.nord.repository.entity.Link;
import cod.nord.service.model.LinkRequest;
import cod.nord.service.model.LinkResponse;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

public interface LinkService {

    @Nonnull
    Collection<LinkResponse> findAll();

    @Nonnull
    LinkResponse getById(int id);

    int create(@Nonnull LinkRequest requested);

    @Nonnull
    LinkResponse update(int id, @Nonnull LinkRequest requested);

    void delete(int id);

    Optional<Link> getByPath(String path);
}
