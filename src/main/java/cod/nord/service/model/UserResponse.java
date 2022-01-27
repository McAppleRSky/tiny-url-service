package cod.nord.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {

    private Integer id;
    private String username;

}
