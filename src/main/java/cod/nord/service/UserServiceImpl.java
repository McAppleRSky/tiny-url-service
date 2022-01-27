package cod.nord.service;

import cod.nord.repository.PersonDao;
import cod.nord.repository.entity.User;
import cod.nord.service.model.UserRequest;
import cod.nord.service.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PersonDao personDao;

    @Transactional(readOnly = true)
    @Override @Nonnull
    public List<UserResponse> findAll() {
//        return personDao.findAll().stream().map(UserHelper::buildResponse).collect(toList());
        throw new NotImplementedException("List<UserResponse> findAll()");
    }

    @Transactional(readOnly = true)
    @Override @Nonnull
    public UserResponse getById(int id) {
//        return ofNullable(personDao.findById(id)).map(UserHelper::buildResponse).orElseThrow(() -> new EntityNotFoundException("User '" + id + "' not found"));
        throw new NotImplementedException("UserResponse getById(int id)");
    }

    @Override
    public int create(@Nonnull UserRequest requested) {
        User creating = new User();
        creating.setName(requested.getName());
        creating.setEmail(requested.getEmail());
        creating.setPassword(requested.getPassword());
        User created = personDao.save(creating);
        return created.getId();
    }

    @Transactional
    @Override @Nonnull
    public UserResponse update(int id, @Nonnull UserRequest requested) {
        User updating = personDao.findById(id);
        if (updating==null){
            throw new EntityNotFoundException("User '" + id + "' not found");
        } else {
            updating.setEmail(requested.getEmail());
            updating.setPassword(requested.getPassword());
            personDao.update(updating);
        }
        return getById(id);
    }

    @Transactional
    @Override
    public void delete(int id) {
        personDao.delete(id);
    }

    @Override
    public Optional<User> getByLogin(String username) {
        throw new NotImplementedException("User getByLogin(String username)");
    }

}
