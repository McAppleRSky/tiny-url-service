package cod.nord.service.model;

import cod.nord.repository.entity.User;

public class UserHelper {

    public static UserResponse buildResponse(User user) {
        return new UserResponse()
                .setId(user.getId())
                .setName(user.getName());
    }

}
