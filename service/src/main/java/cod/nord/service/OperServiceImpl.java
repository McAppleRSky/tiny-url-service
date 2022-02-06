package cod.nord.service;

import cod.nord.repository.OperRepository;
import cod.nord.repository.entity.Link;
import cod.nord.repository.entity.Oper;
import cod.nord.service.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OperServiceImpl implements OperService {

    private final OperRepository operRepository;

//    @Value("${boot.security.user}") private String bootUser;

    @Transactional(readOnly = true)
    @Override @Nonnull
    public List<OperResponse> findAll() {
//        throw new NotImplementedException("List<UserResponse> findAll()");
        return operRepository.findAll()
                .stream()
                .map(ModelHelper::buildResponse)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override @Nonnull
    public OperResponse getById(int id) {
//        throw new NotImplementedException("UserResponse getById(int id)");
        return ofNullable(operRepository.findById(id))
                .map(ModelHelper::buildResponse)
                .orElseThrow(() -> new EntityNotFoundException("User '" + id + "' not found"));
    }

    @Transactional
    @Override @Nonnull
    public int create(@Nonnull OperRequest requested) {
        Oper creating = new Oper();
        creating.setName(requested.getName());
        creating.setLogin(requested.getLogin());
        creating.setEmail(requested.getEmail());
        creating.setPassword(requested.getPassword());
        Oper created = operRepository.save(creating);
        return created.getId();
    }

    @Transactional
    @Override @Nonnull
    public OperResponse update(int id, @Nonnull OperRequest requested) {
        Oper updating = operRepository.findById(id);
        if (updating==null){
            throw new EntityNotFoundException("Operator '" + id + "' not found");
        } else {
            updating.setName(requested.getName());
            updating.setEmail(requested.getEmail());
            updating.setPassword(requested.getPassword());
            operRepository.update(updating);
        }
        return getById(id);
    }

    @Transactional
    @Override @Nonnull
    public void delete(int id) {
        operRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override  @Nullable
    public Optional<Oper> getByLogin(String login) {
        return ofNullable(operRepository.findByLogin(login));
    }

    public Optional<Oper> getPrincipalOperator() {
        Object principal = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            principal = authentication.getPrincipal();
        }
        return ofNullable(operRepository.findByLogin(principal.toString()));
    }


    @Nonnull
    Collection<LinkResponse> findAllLink() {
        throw new NotImplementedException("");
    }

    @Nonnull
    LinkResponse getByIdLink(int id) {
        throw new NotImplementedException("");
    }

    public int createLink(@Nonnull LinkRequest requested) {
        /*Oper principal = getPrincipalOperator()
                .orElseThrow(() -> new NotImplementedException("No principal"));
        Set<Link> links = principal.getLinks();
        Link link = new Link(null, requested.getPath(), null *//*requested.getUrl()*//*, Timestamp.valueOf(requested.getExpire()), null, null, null);
        links.add(link);
        Oper updated = operRepository.update(principal);*/
        throw new NotImplementedException("");
    }

    @Nonnull
    LinkResponse updatelink(int id, @Nonnull LinkRequest requested) {
        throw new NotImplementedException("");
    }

    void deleteLink(int id) {
        throw new NotImplementedException("");
    }

    Optional<Link> getByPathLink(String path) {
        throw new NotImplementedException("");
    }

}
