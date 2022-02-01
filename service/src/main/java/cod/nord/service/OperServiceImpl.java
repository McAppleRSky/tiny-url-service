package cod.nord.service;

import cod.nord.repository.OperRepository;
import cod.nord.repository.entity.Oper;
import cod.nord.service.model.OperHelper;
import cod.nord.service.model.OperRequest;
import cod.nord.service.model.OperResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
        return operRepository.findAll().stream()
                .map(OperHelper::buildResponse)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    @Override @Nonnull
    public OperResponse getById(int id) {
//        return ofNullable(personDao.findById(id)).map(UserHelper::buildResponse).orElseThrow(() -> new EntityNotFoundException("User '" + id + "' not found"));
        throw new NotImplementedException("UserResponse getById(int id)");
    }

    @Override
    public int create(@Nonnull OperRequest requested) {
        Oper creating = new Oper();
        creating.setName(requested.getName());
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
            throw new EntityNotFoundException("User '" + id + "' not found");
        } else {
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

}
