package cod.nord.service.model;

import cod.nord.repository.entity.Link;
import cod.nord.repository.entity.Oper;

public class ModelHelper {

    public static OperResponse buildResponse(Oper oper) {
        return new OperResponse(
                oper.getId(),
                oper.getName(),
                oper.getLogin(),
                oper.getPassword(),
                oper.getEmail() );
    }

    public static LinkResponse buildResponse(Link link) {
        return new LinkResponse(
                link.getId(),
                link.getPath(),
                link.getUrl(),
                link.getExpire()
                        .toLocalDateTime(),
                link.getFollowCount(),
                link.getFollowUniqueCount() );
    }

}
