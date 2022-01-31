package cod.nord.service;

import cod.nord.repository.entity.User;
import cod.nord.service.model.UserRequest;
import cod.nord.service.model.UserResponse;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface UserService {

    @Nonnull
    List<UserResponse> findAll();

    @Nonnull
    UserResponse getById(int id);

    int create(@Nonnull UserRequest requested);

    @Nonnull
    UserResponse update(int id, @Nonnull UserRequest requested);

    void delete(int id);

    Optional<User> getByLogin(String login);

    /*void addPersonToDepartment(int departmentId, int personId);
    void removePersonFromDepartment(int departmentId, int personId);
    void setDepartmentDao(DepartmentDao departmentDao);*/

}
