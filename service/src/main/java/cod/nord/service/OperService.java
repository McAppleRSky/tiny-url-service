package cod.nord.service;

import cod.nord.repository.entity.Oper;
import cod.nord.service.model.OperRequest;
import cod.nord.service.model.OperResponse;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public interface OperService {

    @Nonnull
    List<OperResponse> findAll();

    @Nonnull
    OperResponse getById(int id);

    int create(@Nonnull OperRequest requested);

    @Nonnull
    OperResponse update(int id, @Nonnull OperRequest requested);

    void delete(int id);

    Optional<Oper> getByLogin(String login);

    /*void addPersonToDepartment(int departmentId, int personId);
    void removePersonFromDepartment(int departmentId, int personId);
    void setDepartmentDao(DepartmentDao departmentDao);*/

}
