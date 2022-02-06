package cod.nord.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

//@Data
//@Accessors(chain = true)
@Getter
@RequiredArgsConstructor
public class OperResponse {

    private final Integer id;
    private final String name;
    private final String login;
    private final String password;
    private final String email;

}
