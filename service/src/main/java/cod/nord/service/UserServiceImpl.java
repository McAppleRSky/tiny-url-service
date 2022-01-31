package cod.nord.service;

import cod.nord.repository.UserRepository;
import cod.nord.repository.entity.User;
import cod.nord.service.model.UserHelper;
import cod.nord.service.model.UserRequest;
import cod.nord.service.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Value;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Value("${boot.security.user}")
    private String bootUser;

    @Transactional(readOnly = true)
    @Override @Nonnull
    public List<UserResponse> findAll() {
//        throw new NotImplementedException("List<UserResponse> findAll()");
        return userRepository.findAll().stream()
                .map(UserHelper::buildResponse)
                .collect(toList());
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
        User created = userRepository.save(creating);
        return created.getId();
    }

    @Transactional
    @Override @Nonnull
    public UserResponse update(int id, @Nonnull UserRequest requested) {
        User updating = userRepository.findById(id);
        if (updating==null){
            throw new EntityNotFoundException("User '" + id + "' not found");
        } else {
            updating.setEmail(requested.getEmail());
            updating.setPassword(requested.getPassword());
            userRepository.update(updating);
        }
        return getById(id);
    }

    @Transactional
    @Override @Nonnull
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Transactional(readOnly = true)
    @Override  @Nullable
    public Optional<User> getByLogin(String login) {
        return ofNullable(userRepository.findByLogin(login));
    }

}
