package cod.nord.service.model;

import cod.nord.repository.entity.Oper;

public class OperHelper {

    public static OperResponse buildResponse(Oper oper) {
        return new OperResponse()
                .setId(oper.getId())
                .setName(oper.getName());
    }

}
