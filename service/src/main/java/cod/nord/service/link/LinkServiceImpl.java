package cod.nord.service.link;

import cod.nord.repository.LinkRepository;
import cod.nord.repository.OperRepository;
import cod.nord.repository.entity.Link;
import cod.nord.repository.entity.Oper;
import cod.nord.service.codec.CodecService;
import cod.nord.service.model.LinkRequest;
import cod.nord.service.model.LinkResponse;
import cod.nord.service.model.ModelHelper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final OperRepository operRepository;
    private final CodecService codecService;

    @Value("${path.codec.length}")
    private int PATH_LENGTH;

    @Transactional(readOnly = true)
    @Override @Nonnull
    public Collection<LinkResponse> findAll() {
        return linkRepository.findAll()
                .stream()
                .map(ModelHelper::buildResponse)
                .collect(toList());
    }

    @Nonnull
    @Override
    public LinkResponse getById(int id) {
        throw new NotImplementedException("");
//        return null;
    }

    private Optional<Oper> getPrincipalOperator() {
        Object principal = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            principal = authentication.getPrincipal();
        }
        return ofNullable(operRepository.findByLogin(principal.toString()));
    }

    @Transactional
    @Override @Nonnull
    public int create(@Nonnull LinkRequest requested) {
        Oper principal = getPrincipalOperator()
                .orElseThrow(() -> new NotImplementedException("No principal"));
        Link creating = new Link();
        creating.setUrl(
                requested.getUrl() );
        creating.setPath(
                codecService.encode(
                        requested.getUrl() ) );
        creating.setExpire(
                Timestamp.valueOf(
                        requested.getExpire() ) );
        creating.setOper(principal);
        Link created = linkRepository.save(creating);

        /*Oper creating = new Oper();
        creating.setName(requested.getName());
        creating.setLogin(requested.getLogin());
        creating.setEmail(requested.getEmail());
        creating.setPassword(requested.getPassword());
        Oper created = operRepository.save(creating);
        return created.getId();*/
//        throw new NotImplementedException("");
        return created.getId();
    }

    @Transactional
    @Override @Nonnull
    public LinkResponse update(int id, @Nonnull LinkRequest requested) {
//        throw new NotImplementedException("");
        Link updating = linkRepository.findById(id);
        if (updating==null){
            throw new EntityNotFoundException("Link '" + id + "' not found");
        } else {
            updating.setUrl(requested.getUrl());
            updating.setExpire(
                    Timestamp.valueOf(
                            requested.getExpire() ) );
            linkRepository.update(updating);
        }
        return getById(id);
    }

    @Transactional
    @Override @Nonnull
    public void delete(int id) {
//        throw new NotImplementedException("");
        linkRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override  //@Nullable
    public Optional<Link> getByPath(String path) {
        Link link = null;
        if (path.length()==PATH_LENGTH) {
            link = linkRepository.findByPath(path);
        }
        if (link == null) {
            return ofNullable(null);
        }
        LocalDateTime localDateTime = link.getExpire().toLocalDateTime();
        String url = link.getUrl();
        if (localDateTime.isAfter(LocalDateTime.now())) {
            return ofNullable(link);
        } else return ofNullable(null);
    }

}
