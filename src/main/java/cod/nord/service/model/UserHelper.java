package cod.nord.service.model;

import cod.nord.repository.entity.User;
import cod.nord.service.model.UserResponse;

public class UserHelper {

    public static UserResponse buildResponse(User user) {
        return new UserResponse()
                .setId(user.getId())
                .setUsername(user.getUsername());
    }

}
